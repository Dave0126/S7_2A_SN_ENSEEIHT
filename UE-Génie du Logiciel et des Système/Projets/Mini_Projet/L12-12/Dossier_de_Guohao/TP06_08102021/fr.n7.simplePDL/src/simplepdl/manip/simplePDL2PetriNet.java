package simplepdl.manip;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import simplepdl.*;
import simplepdl.Process;
import petrinet.*;

public class simplePDL2PetriNet {
	
public static void main(String[] args) {
		
		// Charger le package Petrinet afin de l'enregistrer dans le registre d'Eclipse.
		//PetrinetPackage packageInstance = PetrinetPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(SimplepdlPackage.eNS_URI, SimplepdlPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(PetrinetPackage.eNS_URI, PetrinetPackage.eINSTANCE);

		// Enregistrer l'extension ".xmi" comme devant Ãªtre ouverte Ã 
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
		
		/**CHARGEMENT SIMPLEPDL**/
		//EPackage.Registry.INSTANCE.put(SimplepdlPackage.eNS_URI, SimplepdlPackage.eINSTANCE);
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.FILE_EXTENSION, Resource.Factory.INSTANCE);
		ResourceSet resGet = new ResourceSetImpl();
		
		// Charger la ressource (notre modÃ¨le)
		URI modelURI_GET = URI.createURI("models/ExempleSimplepdl.xmi");
		//URI modelURI_GET = URI.createURI("Process.xmi");
		Resource resource_SimplePDL = resGet.getResource(modelURI_GET, true);
		
		// RÃ©cupÃ©rer le premier Ã©lÃ©ment du modÃ¨le (Ã©lÃ©ment racine)
		Process process = (Process) resource_SimplePDL.getContents().get(0);
		
		/**CREATION PETRINET**/
		
		// CrÃ©er un objet resourceSetImpl qui contiendra une ressource EMF (le modÃ¨le)
		ResourceSet resSet = new ResourceSetImpl();
		
		// DÃ©finir la ressource (le modÃ¨le)
		URI modelURI_SET = URI.createURI("models/PetriNetCreator_Process.xmi");
		Resource resource_PetriNet = resSet.createResource(modelURI_SET);
		
		
				
		
		// La fabrique pour fabriquer les Ã©lÃ©ments de PetriNet
	    PetrinetFactory myFactory = PetrinetFactory.eINSTANCE;

		// CrÃ©er un Ã©lÃ©ment Process
	    PetriNet petriNet = myFactory.createPetriNet();
		((ENamedElement) petriNet).setName("PetriNET_Correspendant_"+ process.getName());
		
		// Ajouter le petriNet dans le modÃ¨le
		resource_PetriNet.getContents().add(petriNet);
		
		
		
		
		// Ajouter les ressources
		for (simplepdl.Ressource res : process.getRessources()) {
			// Creation des Places 
			Place p1 = myFactory.createPlace();
			p1.setName(res.getName());
			p1.setJeton(res.getNbOccurance());
			petriNet.getNodes().add(p1);
			}
		
		
		// Ajouter des WorkDefinitions
		for (WorkDefinition wd : process.getWorkDefinition()) {
			// Creation des Places 
			Place p1 = myFactory.createPlace();
			p1.setName(wd.getName()+"_notStarted");
			p1.setJeton(1);
			Place p2 = myFactory.createPlace();
			p2.setName(wd.getName()+"_inProgress");
			Place p3 = myFactory.createPlace();
			p3.setName(wd.getName()+"_finished");
			Place p4 = myFactory.createPlace();
			p4.setName(wd.getName()+"_started");
			// Creation des Transitions
			Transition t1 = myFactory.createTransition();
			t1.setName(wd.getName()+"_start");
			Transition t2 = myFactory.createTransition();
			t2.setName(wd.getName()+"_finish");
			// Creation des Arcs
			Arc a1 =myFactory.createArc();
			a1.setType(ArcType.NORMAL);
			a1.setSource(p1);
			a1.setTarget(t1);
			Arc a21 =myFactory.createArc();
			a21.setType(ArcType.NORMAL);
			a21.setSource(t1);
			a21.setTarget(p2);
			Arc a22 =myFactory.createArc();
			a22.setType(ArcType.NORMAL);
			a22.setSource(t1);
			a22.setTarget(p4);
			Arc a3 =myFactory.createArc();
			a3.setType(ArcType.NORMAL);
			a3.setSource(p2);
			a3.setTarget(t2);
			Arc a4 =myFactory.createArc();
			a4.setType(ArcType.NORMAL);
			a4.setSource(t2);
			a4.setTarget(p3);
			// Attribuer les Creations a PetriNET
			petriNet.getNodes().add(p1);
			petriNet.getNodes().add(p2);
			petriNet.getNodes().add(p3);
			petriNet.getNodes().add(p4);
			petriNet.getNodes().add(t1);
			petriNet.getNodes().add(t2);
			petriNet.getArcs().add(a1);
			petriNet.getArcs().add(a21);
			petriNet.getArcs().add(a3);
			petriNet.getArcs().add(a4);
			petriNet.getArcs().add(a22);
			
			// Ajouter les parameters

			for (parametre par : wd.getParametres()) {
				// Creation des Places 
				//Demande parameter
				String tmp ;
				Arc ad =myFactory.createArc();
				ad.setType(ArcType.NORMAL);
				ad.setPoid(par.getQte());
				
				tmp = par.getRessource().getName();
				for (Node n : petriNet.getNodes()) 
					if (n.getName().equals(tmp))
						ad.setSource(n);
					
				ad.setTarget(t1);
				petriNet.getArcs().add(ad);
				// Liberer Arc de parameter
				Arc al =myFactory.createArc();
				al.setType(ArcType.NORMAL);
				al.setPoid(par.getQte());
				
				tmp = par.getRessource().getName();
				for (Node n : petriNet.getNodes()) 
					if (n.getName().equals(tmp))
						al.setTarget(n);
					
				al.setSource(t2);
				petriNet.getArcs().add(al);
				}
		}
		
		//Ajouter les WorkSequences
		for (WorkSequence ws : process.getWorkSequence()) {
			Arc ra = myFactory.createArc();
			ra.setType(ArcType.READ_ARC);
			ra.setPoid(1);
			if (ws.getLinkType() == WorkSequenceType.FINISH_TO_FINISH){
				String tmp = ws.getPredecessor().getName()+"_finished";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setSource(n);
				}
				tmp = ws.getSuccessor().getName()+"_finish";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setTarget(n);
				}}
			else if (ws.getLinkType() == WorkSequenceType.START_TO_FINISH){
				String tmp = ws.getPredecessor().getName()+"_started";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setSource(n);
				}
				tmp = ws.getSuccessor().getName()+"_finish";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setTarget(n);
				}}
			else if (ws.getLinkType() == WorkSequenceType.FINISH_TO_START){
				String tmp = ws.getPredecessor().getName()+"_finished";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setSource(n);
				}
				tmp = ws.getSuccessor().getName()+"_start";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setTarget(n);
				}}
			else if (ws.getLinkType() == WorkSequenceType.START_TO_START){
				String tmp = ws.getPredecessor().getName()+"_started";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setSource(n);
				}
				tmp = ws.getSuccessor().getName()+"_start";
				for (Node n : petriNet.getNodes()) {
					if (n.getName().equals(tmp))
						ra.setTarget(n);
				}}
			petriNet.getArcs().add(ra);
		}
		
		
			
			    
		// Sauver la ressource
	    try {
	    	resource_PetriNet.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
