import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

/**
 * Model component of Model-Delegate Paradigm.
 */
public class DrawModel {

  private PropertyChangeSupport notifier;  // tracks and notifies listeners

  private String shape = "Line"; // Currently selected shape to draw
  private Color colour = Color.BLACK; // Currently selected colour
  private float strokeSize = 3;  // Stroke size of line
  private boolean fillToggle = false; // Wheter fill is toggled or not
  private int undosTillClear = -1; // number of shapes since canvas was cleared

  ArrayList<Shape> shapeList = new ArrayList<Shape>(); // List of shapes
  ArrayList<Shape> undoList = new ArrayList<Shape>(); // List of removed shapes
  ArrayList<Shape> clearList = new ArrayList<Shape>(); // List of shapes removed after canvas was cleared

  /**
   * Constructor for Draw Model.
   */
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
      notifier.firePropertyChange("shapeList", shapeList, tempShapeList);
      shapeList = tempShapeList;  // reset for next change

  }

  /**
   * Set the shape to the currently chosen shape in the GUI.
   * @param shape  current shape
   */
  public void setShape(String shape) {
    System.out.println("Shape set to " + shape);
    this.shape = shape;

  }

  /**
   * Set the colour to the currently chosen colour in the GUI.
   * @param colour  current colour
   */
  public void setColour(Color colour) {
    System.out.println("Colour set to " + colour.toString());
    this.colour = colour;

  }

  /**
   * Switches the fill to on or off.
   */
  public void switchFillToggle() {
    fillToggle = !fillToggle;
    System.out.println("Fill toggled is " + fillToggle);

  }

  /**
   * Sets the current stroke size.
   * @param strokeSize  New stroke size.
   */
  public void setStrokeSize(float strokeSize) {
    System.out.println("Stroke size set to " + strokeSize);
    this.strokeSize = strokeSize;

  }

  /**
   * Creates a new shape and adds it to the shape list.
   * @param startX   starting X coordinate of shape
   * @param startY   starting Y coordinate of shape
   * @param finishX  finishing X coordinate of shape
   * @param finishY  finishing Y coordinate of shape
   */
  public void drawShape(int startX, int startY, int finishX, int finishY) {

    ArrayList<Shape> tempShapeList = (ArrayList) shapeList.clone();

    // Create current shape with co-ords and add it to tempShapeList
    if (shape.equals("Line")) {
      tempShapeList.add(new ShapeLine(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Rectangle")) {
      tempShapeList.add(new ShapeRectangle(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Parallelogram")) {
      tempShapeList.add(new ShapeParalellogram(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Triangle")) {
        tempShapeList.add(new ShapeTriangle(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Cross")) {
        tempShapeList.add(new ShapeCross(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Ellipse")) {
      tempShapeList.add(new ShapeEllipse(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Pentagon")) {
      tempShapeList.add(new ShapePentagon(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Hexagon")) {
      tempShapeList.add(new ShapeHexagon(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Octagon")) {
      tempShapeList.add(new ShapeOctagon(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Square")) {
      tempShapeList.add(new ShapeSquare(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    } else if (shape.equals("Circle")) {
      tempShapeList.add(new ShapeCircle(startX, startY, finishX, finishY, colour, fillToggle, strokeSize));
    }

    update(tempShapeList); // Update shapelist

    // If a clear has been performed increment undosTillClear. This allows the
    // clear functions to be undone.
    if (undosTillClear != -1) {
      undosTillClear++;
    }

  }

  /**
   * Clears the shapeList. Called when clear button is pressed.
   */
  public void clearShapeList() {

    if (!shapeList.isEmpty()) { // Check that the list isnt already empty

      clearList.addAll(shapeList); // add all shapes to undo list
      undosTillClear = 0; // Set the number of undosTillClear to 0

      // Update shapeList
      ArrayList<Shape> tempShapeList = new ArrayList<Shape>();
      update(tempShapeList);

      System.out.println("Cleared shape list");
    }

  }

  /**
   * Prints the current shapeList.
   */
  public void printShapeList() {
    System.out.println("Shape list: " + shapeList.toString());
  }

  /**
   * Undos the last shape added to shapeList.
   */
  public void undoShapeList() {

    if (undosTillClear == 0) {      // if we are undoing a clear operation
      shapeList.addAll(clearList);  // add all cleared shapes to the shapelist
      clearList.clear();            // empty clear list
      undosTillClear = -1;          // reset undosTillClear to -1
      return;
    }

    if (!shapeList.isEmpty()) { // Check that shape list isnt empty

      // Adds the last shape to the undoList
      undoList.add(shapeList.get(shapeList.size() - 1));

      // Removes the last shape from the list and updates shapeList
      ArrayList<Shape> tempShapeList = (ArrayList) shapeList.clone();
      tempShapeList.remove(shapeList.size() - 1);
      update(tempShapeList);

      if (undosTillClear > 0) { // If undos till clear isnt -1 decrement it.
        undosTillClear--;
      }
    }

  }

  /**
   * Redos the last undo in the shapeList.
   */
  public void redoShapeList() {

    if (!undoList.isEmpty()) { // Check undoList isnt empty

      ArrayList<Shape> tempShapeList = (ArrayList) shapeList.clone(); // duplicate shapeList
      tempShapeList.add(undoList.get(undoList.size() - 1)); // add the last undone element to it
      undoList.remove(undoList.size() - 1);   // remove last undone element from undo list
      update(tempShapeList); // update shapeList

      if (undosTillClear > 0) { // If undos till clear isnt -1 increment it
        undosTillClear++;
      }

    }

  }

  /**
     * Saves the current shape list to the given file.
     * @param file  file to save to
   */
  public void saveShapeList(File file) {
    try {

      // Save shape list
      FileOutputStream fos = new FileOutputStream(file + ".txt");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(shapeList);
      oos.close();

      System.out.println("Saved shape list:");
      printShapeList();

    } catch (Exception p){
      System.out.println("Save failed");
      p.printStackTrace();
    }

  }

  /**
   * Loads a shape list from the given file.
   * @param file  file to load from
   */
  public void loadShapeList(File file) {
      try {

       FileInputStream fin = new FileInputStream(file);
       ObjectInputStream ois = new ObjectInputStream(fin);

       // Change all lists
       update((ArrayList<Shape>)ois.readObject());
       undosTillClear = -1;
       undoList.clear();
       clearList.clear();

       ois.close();

       System.out.println("Loaded shape list:");
       printShapeList();

      } catch(Exception q) {
        System.out.println("Load failed");
        q.printStackTrace();
      }

    }

    // Getter methods

    public String getShape() {
      return shape;
    }

    public Color getColour() {
      return colour;
    }

    public float getStrokeSize() {
      return strokeSize;
    }

    public boolean getFillToggle() {
      return fillToggle;
    }

    public ArrayList<Shape> getShapeList() {
      return shapeList;
    }

}
