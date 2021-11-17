import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Class representing a Parallelogram.
 */
class ShapeParalellogram extends Shape implements java.io.Serializable {

  // How much the bottom left and top right points should shift by to make it
  private static int PARA_POINT_SHIFT = 25;

  /**
   * Constructor for ShapeParalellogram.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public ShapeParalellogram(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
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

    // Flips the shit value so a Parallelogram can be drawn by dragging the
    // mouse in any diretion
    int posOrNeg = 1;
    if (finishX - startX < 0) {
      posOrNeg = -1;
    }

    // Create polygon shape using 4 coords
    Polygon polygon = new Polygon(new int[] {startX+(posOrNeg*PARA_POINT_SHIFT), startX, finishX-(posOrNeg*PARA_POINT_SHIFT), finishX}, new int[] {startY, finishY, finishY, startY}, 4);

    // Draw shape with or without fill depending on fillToggle
    if (fillToggle == true) {
      g2d.fill(polygon);
    } else {
      g2d.draw(polygon);
    }

  }

}
