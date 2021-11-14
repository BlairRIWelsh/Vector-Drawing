import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

  protected Color colour;
  protected boolean fillToggle;
  protected int startX;
  protected int startY;
  protected int finishX;
  protected int finishY;
  protected float strokeSize;

  public Shape(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    this.startX = startX;
    this.startY = startY;
    this.finishX = finishX;
    this.finishY = finishY;
    this.colour = colour;
    this.fillToggle = fillToggle;
    this.strokeSize = strokeSize;
  }

	public abstract void draw(Graphics g);

}
