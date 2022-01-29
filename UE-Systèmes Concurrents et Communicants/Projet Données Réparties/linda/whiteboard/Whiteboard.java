/*
** @author philippe.queinnec@enseeiht.fr
** Inspired by IBM TSpaces exemples.
**
**/

package linda.whiteboard;

public class Whiteboard {

    /*** main **
     ** Run the whiteboard as an application.
     **
     ** @param args - command line arguments
     */
    public static void main(String args[]) {
    	if (args.length != 1) {
    		System.err.println("Whiteboard serverURI.");
    		return;
    	}
        WhiteboardModel model = new WhiteboardModel();
        WhiteboardView view = new WhiteboardView(model);
        model.setView(view);
        model.start(new linda.server.LindaClient(args[0]));
    }
}

