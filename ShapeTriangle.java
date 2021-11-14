import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.lang.Math.*;

class ShapeTriangle extends Shape {

  public ShapeTriangle(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    super(startX, startY, finishX, finishY, colour, fillToggle, strokeSize);

  }

  public void draw(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(colour);
    g2d.setStroke(new BasicStroke(strokeSize));

    int width = finishX - startX;

    Polygon polygon = new Polygon(new int[] {startX, startX+(width/2), finishX}, new int[] {finishY, startY, finishY}, 3);

    if (fillToggle == false) { //change to fill
      g2d.draw(polygon);
    } else {
      g2d.fill(polygon);
    }

  }

}
