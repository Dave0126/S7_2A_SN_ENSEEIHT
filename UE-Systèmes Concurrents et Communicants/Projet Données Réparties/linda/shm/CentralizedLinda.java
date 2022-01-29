package linda.shm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import linda.Callback;
import linda.Linda;
import linda.Tuple;

/** Shared memory implementation of Linda. */
public class CentralizedLinda implements Linda {
	
	private ArrayList<Tuple> tuples;
	
	private HashMap<Tuple, ArrayList<Callback>> readCallbacks;
	private HashMap<Tuple, ArrayList<Callback>> takeCallbacks;
	
	private ReentrantLock accesTuples;
	private ReentrantLock accesCallbacks;
	
    public CentralizedLinda() {
    	tuples = new ArrayList<>();
    	readCallbacks = new HashMap<>();
    	takeCallbacks = new HashMap<>();
    	accesTuples = new ReentrantLock();
		accesCallbacks = new ReentrantLock();
    }

	// Retourne la liste des callbacks abonnés à 'template' dans 'callbacksMap'
	private ArrayList<Callback> getCallbacks(Tuple template, HashMap<Tuple, ArrayList<Callback>> callbacksMap) {
		accesCallbacks.lock();
		for(Tuple key : callbacksMap.keySet()) {
			if (template.matches(key)) {
				accesCallbacks.unlock();
				return (callbacksMap.get(key));
			}
		}
		accesCallbacks.unlock();
		return null;
	}

	@Override
	public void write(Tuple t) {
		// Signal aux callbacks concernés la réception d'un nouveau tuple
		// Reveils des callbacks READ
		ArrayList<Callback> cbList = getCallbacks(t, readCallbacks);
		accesCallbacks.lock();
		if (cbList != null && !cbList.isEmpty()) {
			ArrayList<Callback> toRemove = new ArrayList<>();
			for(Callback cb : cbList) {
				cb.call(t);
				toRemove.add(cb);
			}
			cbList.removeAll(toRemove);
		}
		accesCallbacks.unlock();

		// Reveil du plus ancien callback TAKE
		cbList = getCallbacks(t, takeCallbacks);
		accesCallbacks.lock();
		if (cbList != null && !cbList.isEmpty()){
			cbList.get(0).call(t);
			cbList.remove(0);
			accesCallbacks.unlock();
			return;
		}
		accesCallbacks.unlock();

		// Aucun callback TAKE concerné : ajout du tuple à l'espace
		accesTuples.lock();
		tuples.add(t);
		accesTuples.unlock();
	}

	@Override
	public Tuple take(Tuple template) {
		Tuple alreadyPresent = this.tryTake(template);

		if (alreadyPresent == null) {
			// Recherche dans l'espace des tuples avec mode FUTURE
			SynchronousCallback cb = new SynchronousCallback();
			this.eventRegister(eventMode.TAKE, eventTiming.FUTURE, template, cb);

			// Attente du call
			synchronized(cb) {
				try {
					cb.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Reveil
			return cb.getResultat();
		} else {
			return alreadyPresent;
		}
	}

	@Override
	public Tuple read(Tuple template) {
		Tuple alreadyPresent = this.tryRead(template);

		if (alreadyPresent == null) {
			// Recherche dans l'espace des tuples avec mode FUTURE
			SynchronousCallback cb = new SynchronousCallback();
			this.eventRegister(eventMode.READ, eventTiming.FUTURE, template, cb);

			// Attente du call
			synchronized(cb) {
				try {
					cb.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Reveil
			return cb.getResultat();
		} else {
			return alreadyPresent;
		}
	}

	@Override
	public Tuple tryTake(Tuple template) {
		// Recherche dans l'espace des tuples
		accesTuples.lock();
		for (Tuple t : tuples) {
			if (t.matches(template)) {
				tuples.remove(t);
				accesTuples.unlock();
				return t;
			}
		}
		accesTuples.unlock();
		
		return null;
	}

	@Override
	public Tuple tryRead(Tuple template) {
		// Recherche dans l'espace des tuples
		accesTuples.lock();
		for (Tuple t : tuples) {
			if (t.matches(template)) {
				accesTuples.unlock();
				return t;
			}
		}
		accesTuples.unlock();
		
		return null;
	}

	@Override
	public Collection<Tuple> takeAll(Tuple template) {
		ArrayList<Tuple> res = new ArrayList<>();

		// Recherche dans l'espace des tuples
		accesTuples.lock();
		for (Tuple t : tuples) {
			if (t.matches(template)) {
				res.add(t);
				tuples.remove(t);
			}
		}
		accesTuples.unlock();

		return res;
	}

	@Override
	public Collection<Tuple> readAll(Tuple template) {
		ArrayList<Tuple> res = new ArrayList<>();

		// Recherche dans l'espace des tuples
		accesTuples.lock();
		for (Tuple t : tuples) {
			if (t.matches(template)) {
				res.add(t);
			}
		}
		accesTuples.unlock();
		
		return res;
	}

	@Override
	public void eventRegister(eventMode mode, eventTiming timing, Tuple template, Callback callback) {
		Tuple res = null;
		ArrayList<Callback> cbList = null;
		if (timing == eventTiming.IMMEDIATE) {
			if (mode == eventMode.READ) {
				res = tryRead(template);
			} else if (mode == eventMode.TAKE) {
				res = tryTake(template);
			}
		}

		// Aucune correspondance : enregistrement du callback
		if (res == null) {
			accesCallbacks.lock();
			if (mode == eventMode.READ) {
				cbList = readCallbacks.get(template);
				if (cbList == null) {
					readCallbacks.put(template,new ArrayList<>(Arrays.asList(callback)));
				} else {
					cbList.add(callback);
				}
			} else if (mode == eventMode.TAKE){
				cbList = takeCallbacks.get(template);
				if (cbList == null) {
					takeCallbacks.put(template,new ArrayList<>(Arrays.asList(callback)));
				} else {
					cbList.add(callback);
				}
			}
			accesCallbacks.unlock();
		// Correspondance : reveil du callback
		} else {
			callback.call(res);
		}
	}

	@Override
	public void debug(String prefix) {
		Tuple t = null;
		StringBuilder sb = new StringBuilder(prefix + " Tuples : ");
		accesTuples.lock();
		if (tuples.isEmpty()) {
			sb.append("No Tuples stored");
		} else {
			for(int i = 0 ; i < this.tuples.size() ; i++) {
				t = this.tuples.get(i);
				sb.append(t.toString());
			}
		}
		accesTuples.unlock();

		System.out.println(sb.toString());
	}
}
