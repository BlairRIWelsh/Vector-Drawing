import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;

class ShapeParalellogram extends Shape {

  private static int PARA_POINT_SHIFT = 25;

  public ShapeParalellogram(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    super(startX, startY, finishX, finishY, colour, fillToggle, strokeSize);

  }

  public void draw(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(colour);
    g2d.setStroke(new BasicStroke(strokeSize));

    int posOrNeg = 1;
    if (finishX - startX < 0) {
      posOrNeg = -1;
    }

    Polygon polygon = new Polygon(new int[] {startX+(posOrNeg*PARA_POINT_SHIFT), startX, finishX-(posOrNeg*PARA_POINT_SHIFT), finishX}, new int[] {startY, finishY, finishY, startY}, 4);

    if (fillToggle == true) { //change to fill
      g2d.fill(polygon);
    } else {
      g2d.draw(polygon);
    }

  }

}
