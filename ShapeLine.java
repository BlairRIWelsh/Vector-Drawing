import java.awt.Graphics;
import java.awt.Color;

class ShapeLine extends Shape {

  public ShapeLine(int startX, int startY, int finishX, int finishY, Color colour) {
    super(startX, startY, finishX, finishY, colour);
    
  }

  public void draw(Graphics g) {
    g.setColor(colour);
    g.drawLine(startX, startY, finishX, finishY);

  }

}
