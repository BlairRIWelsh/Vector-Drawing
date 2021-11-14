import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

class ShapeEllipse extends Shape {

  public ShapeEllipse(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);

  }

  public void draw(Graphics g) {

    g.setColor(colour);

    Graphics2D g2d = (Graphics2D) g;

    int width = finishX - startX;
    int height = finishY-startY;
    int X = startX;
    int Y = startY;

    if (width < 0) {
      X = finishX;
      width = width * -1;
    }
    if (height < 0) {
      Y = finishY;
      height = height * -1;
    }

    g2d.draw(new Ellipse2D.Double(X, Y, width, height));

  }

}
