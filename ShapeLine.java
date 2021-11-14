import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

class ShapeLine extends Shape {

  public ShapeLine(int startX, int startY, int finishX, int finishY, Color colour, boolean fillToggle, float strokeSize) {
    super(startX, startY, finishX, finishY, colour, fillToggle, strokeSize);

  }

  public void draw(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    g.setColor(colour);
    g2d.setStroke(new BasicStroke(strokeSize));

    g.drawLine(startX, startY, finishX, finishY);

  }

}
