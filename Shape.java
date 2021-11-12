import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

  protected Color colour;
  protected int startX;
  protected int startY;
  protected int finishX;
  protected int finishY;

  public Shape(int startX, int startY, int finishX, int finishY) {
    this.startX = startX;
    this.startY = startY;
    this.finishX = finishX;
    this.finishY = finishY;
  }

	public abstract void draw(Graphics g);

}
