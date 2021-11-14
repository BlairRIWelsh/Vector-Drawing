import java.awt.Graphics;
import java.awt.Color;

class ShapeParalellogram extends Shape {

  private static int PARA_POINT_SHIFT = 25;

  public ShapeParalellogram(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);

  }

  public void draw(Graphics g) {
    g.setColor(colour);

    int posOrNeg = 1;
    if (finishX - startX < 0) {
      posOrNeg = -1;
    }

    g.drawPolygon(new int[] {startX+(posOrNeg*PARA_POINT_SHIFT), startX, finishX-(posOrNeg*PARA_POINT_SHIFT), finishX}, new int[] {startY, finishY, finishY, startY}, 4);

  }

}
