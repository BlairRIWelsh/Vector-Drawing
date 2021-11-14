import java.awt.Graphics;
import java.awt.Color;

class ShapeTriangle extends Shape {

  public ShapeTriangle(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);

  }

  public void draw(Graphics g) {
    g.setColor(colour);

    int width = finishX - startX;

    g.drawPolygon(new int[] {startX, startX+(width/2), finishX}, new int[] {finishY, startY, finishY}, 3);

  }

}
