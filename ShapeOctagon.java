import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.lang.Math.*;

/**
 * Class representing a Octagon.
 */
class ShapeOctagon extends Shape implements java.io.Serializable {

  /**
   * Constructor for ShapeOctagon.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public ShapeOctagon(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
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

    // Create polygon shape using 8 points
    Polygon polygon = new Polygon(new int[] {startX + (7*width/24), startX, startX, startX + (7*width/24), startX+(17*width/24), finishX, finishX, startX+(17*width/24)}, new int[] {startY, startY + (7*height/24), startY + (17*(height/24)), finishY, finishY, startY + (17*(height/24)), startY + (7*height/24), startY}, 8);

    // Draw shape with or without fill depending on fillToggle
    if (fillToggle == false) { //change to fill
      g2d.draw(polygon);
    } else {
      g2d.fill(polygon);
    }

  }

}
