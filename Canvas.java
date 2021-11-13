import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private Graphics2D g2;

	public Canvas(){
		setBackground(Color.white);
	}

	public void paint(Graphics g){

		// super.paint(g2);
		//
		Graphics2D g2d = (Graphics2D) g;

		//System.out.println("In paint: " + shapeList.toString());

		for (Shape shape : shapeList) {

			if (shape instanceof Line){
				shape.draw(g);
			}

		}
		repaint();
	}

	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	public void setShapeList(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;
	}
}
