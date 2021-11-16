import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract class representing a shape to be drawn.
 */
public abstract class Shape {

  protected Color colour;
  protected boolean fillToggle;
  protected int startX;
  protected int startY;
  protected int finishX;
  protected int finishY;
  protected float strokeSize;

  /**
   * Constructor for shape.
   * @param startX      starting X coord
   * @param startY      starting Y coord
   * @param finishX     finishing X coord
   * @param finishY     finishing Y coord
   * @param colour      colour
   * @param fillToggle  if fill is toggled or not
   * @param strokeSize  size of stroke
   */
  public Shape(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    this.startX = startX;
    this.startY = startY;
    this.finishX = finishX;
    this.finishY = finishY;
    this.colour = colour;
    this.fillToggle = fillToggle;
    this.strokeSize = strokeSize;
  }

  /**
   * Abstract method allowing a shape to be drawn.
   * @param g  graphics
   */
	public abstract void draw(Graphics g);

}
