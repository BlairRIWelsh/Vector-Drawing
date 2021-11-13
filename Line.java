import java.awt.Graphics;

class Line extends Shape {

  public Line(int startX, int startY, int finishX, int finishY) {
    super(startX, startY, finishX, finishY);
  }

  public void draw(Graphics g) {
    //System.out.println("printing line at " + startX + "," + startY + " to " + finishX + "," + finishY);

    g.drawLine(startX, startY, finishX, finishY);
  }

}
