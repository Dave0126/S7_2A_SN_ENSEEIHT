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

/**
 ** The graphic part of the whiteboard.
 **
 */
public class WhiteboardView implements MouseListener, MouseMotionListener {

    /** clear graphics before drawing lines (an erase command has been received) */
    private boolean clearFlag = false;
    
    public enum DrawMode { LINES, POINTS, OTHER };
    private DrawMode mode = DrawMode.LINES;

    private static Color BACKGROUND_COLOR = Color.white;
    private int x1, y1;
    private int x2, y2;
    private int xl, yl;
    private WhiteboardControls controls;
    private WhiteboardModel model;
    protected Panel drawing; // where lines are drawn

    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;

    public WhiteboardView(WhiteboardModel model) {
        this.model = model;
        
        Frame appFrame = new Frame("Whiteboard");
        appFrame.setSize(WIDTH,HEIGHT);   
        appFrame.setVisible(true);
        appFrame.setLayout(new BorderLayout());

        drawing = new Panel();
        appFrame.add("Center", drawing);

        controls = new WhiteboardControls(this, this.model);
        appFrame.add("North", controls);

        drawing.setBackground(BACKGROUND_COLOR);
        drawing.addMouseMotionListener(this);
        drawing.addMouseListener(this);

        // Handle the user exiting/killing the application
        appFrame.addWindowListener(new WindowAdapter()  {
                  public void windowClosing (WindowEvent e) {
                      model.terminate();
                  }
              } );
        
        Graphics g = drawing.getGraphics();
        if (g != null)
          this.paint(g);
        setDrawMode(DrawMode.LINES);
    }

    /** Force the window to be cleared before drawing the lines. */
    public void setClear() {
        clearFlag = true;
    }

    /** Redraw all the lines. */
    public void redraw() {
        Graphics g = drawing.getGraphics();
        synchronized (model) {
            DrawMode savemode = mode;
            mode = DrawMode.OTHER;
            this.paint(g);
            setDrawMode(savemode);
        }
    }

    /**
     ** Paints the panel based on the set of shapes
     ** If an Erase request was received from TupleSpace then the
     ** clearFlag was set and we will erase the window before
     ** proceeding.
     */
    private void paint(Graphics g) {
        if (clearFlag) {
            g.setColor(drawing.getBackground());
            g.fillRect(0,0, drawing.getSize().width, drawing.getSize().height);
            clearFlag = false;
        }
        g.setColor(drawing.getForeground());
        g.setPaintMode();
        for (ColoredShape rc : model.getLines()) {
            g.setColor(rc.color);
            ((Graphics2D)g).draw(rc.shape);
        }
        if (mode == DrawMode.LINES) {
            g.setXORMode(drawing.getBackground());
            if (xl != -1) { // erase the last line.
                g.drawLine(x1, y1, xl, yl);
            }
            g.setColor(drawing.getForeground());
            g.setPaintMode();
            if (x2 != -1) {
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    /**
     ** Set the draw mode.
     ** @param mode - the draw mode
     */
    public void setDrawMode(DrawMode mode) {
        switch (mode) {
          case LINES:
          case POINTS:
            this.mode = mode;
            break;
          default:
            throw new IllegalArgumentException();
        }
    }

    /**
     ** The mouse was dragged.
     ** @param e - the mouse event
     */
    public void mouseDragged(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            xl = x2;
            yl = y2;
            x2 = e.getX();
            y2 = e.getY();
            break;
          case POINTS:
          default:
            model.addShape(new ColoredShape(x1, y1, e.getX(), e.getY(), drawing.getForeground()));
            x1 = e.getX();
            y1 = e.getY();
            break;
        }
        redraw();
    }

    /**
     ** The mouse was moved.
     ** @param e - the mouse event
     */
    public void mouseMoved(MouseEvent e) {
        // not much to do here
    }

    /**
     ** The mouse button was pressed.
     ** @param e - the mouse event
     */
    public void mousePressed(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            x1 = e.getX();
            y1 = e.getY();
            x2 = -1;
            break;
          case POINTS:
          default:
            //model.addShape(new ColoredShape(e.getX(), e.getY(), -1, -1, drawing.getForeground()));
            model.addShape(new ColoredShape(e.getX(), e.getY(), e.getX(), e.getY(), drawing.getForeground()));
            x1 = e.getX();
            y1 = e.getY();
            drawing.repaint();
            break;
        }
    }

    /**
     ** The mouse button was released.
     ** @param e - the mouse event
     */
    public void mouseReleased(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            model.addShape(new ColoredShape(x1, y1, e.getX(), e.getY(), drawing.getForeground()));
            x2 = xl = -1;
            break;
          case POINTS:
          default:
            break;
        }
        redraw();
    }

    /**
     ** The mouse entered this panel.
     ** @param e - the mouse event
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     ** The mouse left this panel.
     ** @param e - the mouse event
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     ** The mouse button was clicked.
     ** @param e - the mouse event
     */
    public void mouseClicked(MouseEvent e) {
    }

}

