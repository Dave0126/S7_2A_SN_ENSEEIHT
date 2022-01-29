/*
** @author philippe.queinnec@enseeiht.fr
** Based on IBM TSpaces exemples.
**
**/

package linda.whiteboard;

import java.awt.event.*;
import java.awt.*;

/**
 ** The controls for the whiteboard.
 ** 
 */
public class WhiteboardControls extends Panel implements ItemListener {

    private WhiteboardView view;
    private WhiteboardModel model;
    private static String ERASEALL_LABEL = "Erase All";
    private static String EXIT_LABEL = "Exit";
    private static String ROTATE_LABEL = "Rotate";
    private static String EXCLUSIVE_LABEL = "Exclusive Access";
    private static String PENDING_LABEL = "Pending...";
    private static String RELEASE_LABEL = "Release Exclusive";

    private Button eraseAllButton;
    private Button rotateButton;
    private Button exitButton;
    
    private Button exclusiveButton;
    private ExclusiveButtonAction exclusiveButtonAction;
    
    /**
     ** The constructor.
     **
     ** @param target - the whiteboard panel
     */
    public WhiteboardControls(WhiteboardView view, WhiteboardModel model) {
        
        this.view = view;
        this.model = model;
        Panel pLine1 = new Panel();
        Panel pLine2 = new Panel();
        setLayout(new GridLayout(2,1));
        add(pLine1);
        add(pLine2);
        
        pLine1.setLayout(new FlowLayout());
        
        eraseAllButton = new Button();
        eraseAllButton.setLabel(ERASEALL_LABEL);
        eraseAllButton.addActionListener((e) -> { model.eraseAll(); });
        pLine1.add(eraseAllButton);
        
        rotateButton = new Button();
        rotateButton.setLabel(ROTATE_LABEL);
        rotateButton.addActionListener((e) -> { model.rotateAll(90); });
        pLine1.add(rotateButton);

        exclusiveButton = new Button();
        exclusiveButton.setLabel(EXCLUSIVE_LABEL);
        exclusiveButtonAction = new ExclusiveButtonAction(exclusiveButton, model);
        exclusiveButton.addActionListener(exclusiveButtonAction);
        pLine1.add(exclusiveButton);
        
        exitButton = new Button();
        exitButton.setLabel(EXIT_LABEL);
        exitButton.addActionListener((e) -> { model.terminate(); });
        pLine1.add(exitButton);

        pLine1.setBackground(Color.lightGray);
        pLine2.setBackground(Color.lightGray);
                
        view.drawing.setForeground(Color.red);
        CheckboxGroup group = new CheckboxGroup();
        Checkbox b;
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.red);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.green);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.blue);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.pink);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.orange);
        pLine2.add(b = new Checkbox(null, group, true));
        b.addItemListener(this);
        b.setBackground(Color.black);
        view.drawing.setForeground(b.getForeground());
        
        Choice shapes = new Choice();
        shapes.addItemListener(this);
        shapes.addItem("Lines");
        shapes.addItem("Points");
        shapes.setBackground(Color.lightGray);
        pLine2.add(shapes);
    } // WhiteboardControls

    /**
     ** Paints me.
     **
     ** @param g - a graphics context
     */
    public void paint(Graphics g) {
        Rectangle r = getBounds();
        g.setColor(Color.lightGray);
        g.draw3DRect(0, 0, r.width, r.height, false);
    }

    /**
     ** The state of the item was changed.
     **
     ** @param e - the item event
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof Checkbox) {
            view.drawing.setForeground(((Component)e.getSource()).getBackground());
        } else if (e.getSource() instanceof Choice) {
            String choice = (String) e.getItem();
            if (choice.equals("Lines")) {
                view.setDrawMode(WhiteboardView.DrawMode.LINES);
            } else if (choice.equals("Points")) {
                view.setDrawMode(WhiteboardView.DrawMode.POINTS);
            } 
        }
    }

    /**
     ** Handling of button AcquireExclusive/Pending/ReleaseExclusive.
     */
    private class ExclusiveButtonAction implements ActionListener {

        protected boolean hasExclusive = false;
        private Button button;
        private WhiteboardModel target;

        public ExclusiveButtonAction(Button b, WhiteboardModel t) {
            button = b;
            target = t;
        }

       public void actionPerformed(ActionEvent e) {
            if (! hasExclusive) {
                target.acquireExclusiveAccess();
                exclusiveButtonAction.hasExclusive = true;
                exclusiveButton.setLabel(RELEASE_LABEL);
            } else {
                target.releaseExclusiveAccess();
                exclusiveButton.setLabel(EXCLUSIVE_LABEL);
                hasExclusive = false;
            }
        }
    }

}

