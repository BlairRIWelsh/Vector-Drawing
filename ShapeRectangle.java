import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

class ShapeRectangle extends Shape {

  public ShapeRectangle(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);

  }

  public void draw(Graphics g) {

    g.setColor(colour);

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

    g.drawRect(X, Y, width, height);

  }

}
