import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.lang.Math.*;

/**
 * Class representing a rectangle.
 */
class ShapeRectangle extends Shape {

  /**
   * Constructor for ShapeRectangle.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public ShapeRectangle(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
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

    // Get starting (top left) coord as well as height and width
    int width = finishX - startX;
    int height = finishY - startY;
    int X = startX;
    int Y = startY;

    // In case width or height is negative find the actual top left coord and
    // make height and width posotive.
    // This allows the cross to be drawn by dragging the mouse in any direction.
    if (width < 0) {
      X = finishX;
      width = width * -1;
    }
    if (height < 0) {
      Y = finishY;
      height = height * -1;
    }

    // Create rectangle shape
    Rectangle rectangle = new Rectangle(X, Y, width, height);

    // Draw shape with or without fill depending on fillToggle
    if (fillToggle == true) {
      g2d.fill(rectangle);
    } else {
      g2d.draw(rectangle);
    }

  }

}
