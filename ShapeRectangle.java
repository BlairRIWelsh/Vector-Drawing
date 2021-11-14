import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.lang.Math.*;

class ShapeRectangle extends Shape {

  public ShapeRectangle(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    super(startX, startY, finishX, finishY, colour, fillToggle, strokeSize);

  }

  public void draw(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(colour);
    g2d.setStroke(new BasicStroke(strokeSize));

    int width = finishX - startX;
    int height = finishY - startY;
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

    Rectangle rectangle = new Rectangle(X, Y, width, height);

    if (fillToggle == true) { //change to fill
      g2d.fill(rectangle);
    } else {
      g2d.draw(rectangle);
    }

  }

}
