import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math.*;

class ShapeCross extends Shape {

  private static int CROSS_THICKNESS_SCALER = 5; // width of the cross

  public ShapeCross(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);

  }

  public void draw(Graphics g) {
    g.setColor(colour);

    // Get starting and finishing points as well as height and width
    int x1 = startX;
    int y1 = startY;
    int x2 = finishX;
    int y2 = finishY;
    int width = finishX - startX;
    int height = finishY - startY;

    // Flip the values in case width or height is negative.
    // This allows the cross to be drawn by dragging the mouse in any direction.
    if (width < 0 && height < 0) {
      x1 = finishX;
      y1 = finishY;
      x2 = startX;
      y2 = startY;
      width = width * -1;
      height = height * -1;
    } else if (width < 0) {
      x1 = finishX;
      x2 = startX;
      width = width * -1;
    } else if (height < 0) {
      y1 = finishY;
      y2 = startY;
      height = height * -1;
    }

    // Find the minimum dimension so we can find a good thickness for the cross
    int q; // thickness of the cross
    if (Math.abs(width) < Math.abs(height)) {
       q = width/CROSS_THICKNESS_SCALER;
    } else  {
      q = height/CROSS_THICKNESS_SCALER;
    }

    // A cross can be thought of as a polygon with 12 points...
    int tlCoordOneX = x1 + q;
    int tlCoordOneY = y1;
    int tlCoordTwoX = x1;
    int tlCoordTwoY = y1 + q;

    int blCoordOneX = x1;
    int blCoordOneY = y2 - q;
    int blCoordTwoX = x1 + q;
    int blCoordTwoY = y2;

    int brCoordOneX = x2;
    int brCoordOneY = y2 - q;
    int brCoordTwoX = x2 - q;
    int brCoordTwoY = y2;

    int trCoordOneX = x2 - q;
    int trCoordOneY = y1;
    int trCoordTwoX = x2;
    int trCoordTwoY = y1 + q;

    int innerNCoordX = x1 + (width/2);
    int innerNCoordY = y1 + (height/2 - q);

    int innerECoordX = x1 + (width/2 + q);
    int innerECoordY = y1 + (height/2);

    int innerSCoordX = x1 + (width/2);
    int innerSCoordY = y1 + (height/2 + q);

    int innerWCoordX = x1 + (width/2 - q);
    int innerWCoordY = y1 + (height/2);

    g.drawPolygon(
      new int[] {
        tlCoordOneX, tlCoordTwoX, innerWCoordX, blCoordOneX, blCoordTwoX, innerSCoordX, brCoordTwoX, brCoordOneX, innerECoordX, trCoordTwoX, trCoordOneX, innerNCoordX},
      new int[] {
        tlCoordOneY, tlCoordTwoY, innerWCoordY, blCoordOneY, blCoordTwoY, innerSCoordY, brCoordTwoY, brCoordOneY, innerECoordY, trCoordTwoY, trCoordOneY, innerNCoordY}
      , 12);

  }

}
