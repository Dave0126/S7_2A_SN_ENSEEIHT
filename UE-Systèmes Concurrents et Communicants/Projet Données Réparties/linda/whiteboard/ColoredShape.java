package linda.whiteboard;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.Color;

public class ColoredShape implements java.io.Serializable {
    public Shape shape;
    public Color color;

    public ColoredShape(Shape s, Color c) {
        shape = s;
        color = c;
    }

    public ColoredShape(int x1, int y1, int x2, int y2, Color c) {
        shape = new Line2D.Double(x1, y1, x2, y2);
        color = c;
    }
}
