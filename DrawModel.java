import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.util.ArrayList;

public class DrawModel {

  private PropertyChangeSupport notifier;  // tracks and notifies listeners
  private String shape = "Line"; // Currently selected shape to draw
  private Color colour = Color.BLACK; // Currently selected colour
  ArrayList<Shape> shapeList = new ArrayList<Shape>();

  public DrawModel() {
      this.notifier = new PropertyChangeSupport(this);
  }

  /**
   * Register a listener so it will be notified of any changes.
   * @param listener  listner
   */
  public void addListener(PropertyChangeListener listener) {
      notifier.addPropertyChangeListener(listener);
  }

  /**
   * Broadcast most recent change to all listeners.
   */
  private void update(ArrayList<Shape> tempShapeList) {

      System.out.println("in draw model: " + shapeList.toString());

      //AbstractAction.firePropertyChange(String propertyName, Object oldValue, Object newValue)
      notifier.firePropertyChange("shapeList", shapeList, tempShapeList);
      shapeList = tempShapeList;  // reset for next change

  }

  public void setShape(String shape) {
    System.out.println("Shape set to " + shape);
    this.shape = shape;
  }

  public void setColour(Color colour) {
    System.out.println("Colour set to " + colour.toString());
    this.colour = colour;
  }

  public void drawShape(int startX, int startY, int finishX, int finishY) {

    ArrayList<Shape> tempShapeList = (ArrayList) shapeList.clone();

    if (shape.equals("Line")) {
      tempShapeList.add(new Line(startX, startY, finishX, finishY));

    } else if (shape.equals("Rectangle")) {

    } else if (shape.equals("Parallelogram")) {

    } else if (shape.equals("Triangle")) {

    } else if (shape.equals("Cross")) {

    } else if (shape.equals("Ellipse")) {

    } else if (shape.equals("Murray Polygon")) {

    }

    System.out.println("Drawn " + shape + " from " + startX + "," + startY + " to " + finishX + "," + finishY);
    update(tempShapeList);
  }

}
