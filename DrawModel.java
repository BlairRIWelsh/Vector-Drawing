import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;

public class DrawModel {

  private double total;  // current total in the calculator
  private double lastTotal;  // total before the last change

  private String shape; // Currently selected shape to draw
  private Color colour; // Currently selected colour

  private PropertyChangeSupport notifier;  // tracks and notifies listeners

  public DrawModel() {
      this.total = 0.0;
      this.lastTotal = 0.0;
      this.notifier = new PropertyChangeSupport(this);
  }

  /** Register a listener so it will be notified of any changes. */
    public void addListener(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    public void setShape(String shape) {
      System.out.println("Shape set to " + shape);
      this.shape = shape;
    }

    public void setColour(Color colour) {
      System.out.println("Colour set to " + colour.toString());
      this.colour = colour;
    }


}
