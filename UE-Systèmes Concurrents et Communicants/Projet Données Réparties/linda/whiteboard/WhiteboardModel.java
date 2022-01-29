/*
** @author philippe.queinnec@enseeiht.fr
** Inspired by IBM TSpaces exemples.
**
**/

package linda.whiteboard;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import linda.*;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;

/**
 ** The model of the whiteboard.
 **
 ** All of the Linda handling is done inside this object.
 **
 ** [ KEY_WHITEBOARD, Command.DRAW, ColorShaped ]
 ** [ KEY_WHITEBOARD, Command.ERASEALL ]
 **
 ** (where KEY_WHITEBOARD="Whiteboard")
 **
 */
public class WhiteboardModel {

    /** This holds a reference to the current Linda. */
    protected Linda linda = null;

    /** The graphic part of the whiteboard. */
    private WhiteboardView view;

    private static final String KEY_WHITEBOARD = "Whiteboard";

    /** The commands that can be sent on the tuple spaces. */
    enum Command { DRAW, ERASEALL, ROTATE };

    /** The lines and their respective colors that this client knows about. */
    private Set<ColoredShape> lines = new HashSet<>();

    private boolean eraseFlag = false;   // set true when erase command received

    private Tuple motifShape = new Tuple(KEY_WHITEBOARD, Command.DRAW, ColoredShape.class);
    private Tuple motifErase = new Tuple(KEY_WHITEBOARD, Command.ERASEALL);
    private Tuple motifRotate = new Tuple(KEY_WHITEBOARD, Command.ROTATE, Integer.class);

    public WhiteboardModel() {
    }
    
    public void setView(WhiteboardView view) {
        this.view = view;
    }

    public Set<ColoredShape> getLines() {
        return lines;
    }

    public void start(Linda linda) {
        this.linda = linda;
        // Create a template to indicate what we are interested in.
        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifErase, new CallbackErase());
        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifShape, new CallbackShape());
        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifRotate, new CallbackRotate());
        System.out.println("Scan for current status");
        // During initialization, we need to read all the current lines
        // stored at the lindaSpaces server.
        Collection<Tuple> tupleSet = linda.readAll(motifShape);
        for (Tuple t : tupleSet) {
            System.out.println("Tuple " + t);
            ColoredShape line = (ColoredShape)t.get(2);
            lines.add(line);
        }

        // and redraw the view
        view.redraw();
    }
    
    /**
     ** This is called when the windowClosing event arrives.
     */
    public void terminate() {
        System.exit(0);
    }

    /** Global Erase of the whiteboard.
     ** Since we will be informed of this in the callback,
     ** we will let the callback update the set of all lines.
     */
    public void eraseAll() {
        System.out.println("Erase all");
        // Delete all rectangle tuples,
        linda.takeAll(new Tuple(KEY_WHITEBOARD, Command.DRAW, ColoredShape.class));
        // Tell all clients that we did an erase by writing an erase tuple,
        linda.write(new Tuple(KEY_WHITEBOARD, Command.ERASEALL));
        // and delete this tuple (potential synchronization problem !)
        linda.takeAll(new Tuple(KEY_WHITEBOARD, Command.ERASEALL));
    }	
    
    /** Rotate all the shapes. */
    public void rotateAll(int degree) {
        System.out.println("Rotate");
        // Tell all clients to rotate by writing a rotate tuple,
        Tuple action = new Tuple(KEY_WHITEBOARD, Command.ROTATE, degree);
        linda.write(action);
        // and delete this tuple (potential synchronization problem !)
        linda.takeAll(action);
    }

    /** Request an exclusive access to the whiteboard.
     * Block until it has succeeded.
     */
    public void acquireExclusiveAccess() {
        System.err.println("Exclusive access: not implemented");
    }

    /** Release the exclusive access. */
    public void releaseExclusiveAccess() {
        System.err.println("Exclusive access: not implemented");
    }

    /**
     ** Publish a new shape (line or point) to the tuple space
     */
    public void addShape (ColoredShape shape)
    {
        // Build a new rectangle tuple and write it into the tuple space.
    	Tuple publish = new Tuple(KEY_WHITEBOARD, Command.DRAW, shape);
    	linda.write(publish);
    }

    /***************************************************************/
    
    private class CallbackShape implements linda.Callback {
		public void call(Tuple t) {
			System.out.println("Draw Request received from server");
                        ColoredShape shape = (ColoredShape)(t.get(2));
                        lines.add(shape);
                        view.redraw();
                        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifShape, this);
                }
    }

    private class CallbackErase implements linda.Callback {
		public void call(Tuple t) {
			System.out.println("Erase Request received from server");
                        lines.clear();
                        view.setClear();
                        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifErase, this);
		}	
    }
    
    private class CallbackRotate implements linda.Callback {
		public void call(Tuple t) {
			System.out.println("Rotate Request received from server");
                        Integer angle = (Integer)(t.get(2));
                        // Let's be careful: rotation with center in WIDTH/2, HEIGHT/2
                        AffineTransform at = new AffineTransform();
                        at.rotate(Math.toRadians(angle), view.drawing.getSize().width / 2.0, view.drawing.getSize().height / 2.0);
                        //at.quadrantRotate(1, view.drawing.getSize().width / 2.0, view.drawing.getSize().height / 2.0);
                        for (ColoredShape rc : lines) {
                            rc.shape = at.createTransformedShape(rc.shape);
                        }
                        view.setClear();
                        view.redraw();
                        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifRotate, this);
                }
    }

}

