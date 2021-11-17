import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 * Class to represent a canvas the user can draw on.
 */
public class Canvas extends JPanel implements java.io.Serializable {

	ArrayList<Shape> shapeList = new ArrayList<Shape>(); // List of shapes on canvas.
	private Graphics2D g2;

	/**
	 * Constructor for canvas.
	 */
	public Canvas(){
		setBackground(Color.white);

	}

	/**
	 * Paints the shapes on a shape list on the canvas.
	 * @param g  Graphics
	 */
	public void paint(Graphics g) {

		// For each shape, draw it on the canvas
		for (Shape shape : shapeList) {
			if (shape instanceof ShapeLine) {
				shape.draw(g);
			} else if (shape instanceof ShapeRectangle) {
				shape.draw(g);
			} else if (shape instanceof ShapeEllipse) {
				shape.draw(g);
			} else if (shape instanceof ShapeTriangle) {
				shape.draw(g);
			} else if (shape instanceof ShapeParalellogram) {
				shape.draw(g);
			} else if (shape instanceof ShapeCross) {
				shape.draw(g);
			} else if (shape instanceof ShapePentagon) {
				shape.draw(g);
			} else if (shape instanceof ShapeHexagon) {
				shape.draw(g);
			} else if (shape instanceof ShapeOctagon) {
				shape.draw(g);
			} else if (shape instanceof ShapeSquare) {
				shape.draw(g);
			} else if (shape instanceof ShapeCircle) {
				shape.draw(g);
			}

		}
		repaint();

	}

	/**
	 * Getter method for the shape list.
	 * @return the shape list as an ArrayList
	 */
	public ArrayList<Shape> getShapeList() {
		return shapeList;

	}

	/**
	 * Sets the shapeList of the canvas.
	 * @param shapeList  new Shape list
	 */
	public void setShapeList(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;

	}

	public void printShapeList() {
	 System.out.println(shapeList.toString());

	}

}
