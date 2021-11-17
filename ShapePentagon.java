import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.lang.Math.*;

/**
 * Class representing a pentagon.
 */
class ShapePentagon extends Shape implements java.io.Serializable {

  /**
   * Constructor for ShapePentagon.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public ShapePentagon(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
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

    int width = (finishX - startX); // find width
    int height = (finishY - startY); // find height

    // Create polygon shape using 5 points
    Polygon polygon = new Polygon(new int[] {startX+(width/2), startX, startX+(width/4), startX+(3*(width/4)), finishX}, new int[] {startY, startY+(height/3), finishY, finishY, startY+(height/3)}, 5);

    // Draw shape with or without fill depending on fillToggle
    if (fillToggle == false) { //change to fill
      g2d.draw(polygon);
    } else {
      g2d.fill(polygon);
    }

  }

}
