import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Class representing a Ellipse.
 */
class ShapeEllipse extends Shape {

  /**
   * Constructor for Ellipse.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public ShapeEllipse(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    super(startX, startY, finishX, finishY, colour, fillToggle, strokeSize);

  }

  /**
   * Method to draw the shape.
   * @param g  graphics
   */
  public void draw(Graphics g) {

    // Set colours and stroke
    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(colour);
    g2d.setStroke(new BasicStroke(strokeSize));

    // Get height, width and top left X and Y coord
    int width = finishX - startX;
    int height = finishY-startY;
    int X = startX;
    int Y = startY;

    // If the width or height is negative (i.e. X and Y are not the top left
    // coord), change X and Y so they are always the top left coord and make
    // height or width posotive. This allows the shape to be drawn by dragging
    // in any direction
    if (width < 0) {
      X = finishX;
      width = width * -1;
    }
    if (height < 0) {
      Y = finishY;
      height = height * -1;
    }

    // Create ellipse shape
    Ellipse2D ellipse = new Ellipse2D.Double(X, Y, width, height);

    // Draw shape with or without fill depending on fillToggle
    if (fillToggle == true) { //change to fill
      g2d.fill(ellipse);
    } else {
      g2d.draw(ellipse);
    }

  }

}
