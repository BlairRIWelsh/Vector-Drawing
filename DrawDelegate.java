import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public class DrawDelegate implements PropertyChangeListener, ActionListener {

  private DrawModel model;

  private static int DEFAULT_FRAME_WIDTH = 750;
  private static int DEFAULT_FRAME_HEIGHT = 750;
  private static int DEFAULT__COLOUR_FRAME_WIDTH = 500;
  private static int DEFAULT_COLOUR_FRAME_HEIGHT = 450;

  private JFrame mainFrame;
  private JMenuBar jmb;
  private Canvas canvas;
  private JToolBar jtb;
  private JLabel selectShapeLabel;
  private JButton colourButton, clearButton, undoButton, redoButton, moveButton,
  lineButton, rectangleButton, parallelogramButton, triangleButton, crossButton,
  ellipseButton, murrayPolygonButton;

  private int startX = -1;
  private int startY = -1;
  private int finishX = -1;
  private int finishY = -1;

  public DrawDelegate(DrawModel model) {
        this.model = model;

        // Create main window
        mainFrame = new JFrame("CS5001 Practical 4 - Vector Drawing");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true); // display frame

        // Create menu bar
        jmb = new JMenuBar();
        addMenuBar();
        mainFrame.setJMenuBar(jmb);

        // Create toolbar
        jtb = new JToolBar();
        addToolbar();
        mainFrame.getContentPane().add(jtb, BorderLayout.NORTH);

        // Create canvas
        canvas = new Canvas();
        mainFrame.getContentPane().add(canvas, BorderLayout.CENTER);
        addActionListenerForCanvas(this);

        // Add the Delegate as a listener
        addActionListenerForButtons(this);
        model.addListener(this);

    }

    /**
     * Adds the action listner to all buttons in the toolbar.
     * @param al  Action listner
     */
    public void addActionListenerForButtons(ActionListener al) {
        colourButton.addActionListener(al);
        clearButton.addActionListener(al);
        undoButton.addActionListener(al);
        redoButton.addActionListener(al);
        moveButton.addActionListener(al);
        lineButton.addActionListener(al);
        rectangleButton.addActionListener(al);
        parallelogramButton.addActionListener(al);
        triangleButton.addActionListener(al);
        crossButton.addActionListener(al);
        ellipseButton.addActionListener(al);
        murrayPolygonButton.addActionListener(al);

    }

    /**
     * Adds the action listner for the canvas.
     * @param al  Action listner
     */
    public void addActionListenerForCanvas(ActionListener al) {

      canvas.addMouseListener(new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {

          if (startX != -1 && startY != -1) {
            finishX = e.getX();
            finishY = e.getY();

            // draw shape
            model.drawShape(startX, startY, finishX, finishY);
            canvas.repaint();

            startX = -1;
            startY = -1;
          }
        }
      });

      canvas.addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {

          if (startX == -1 && startY == -1) {
            startX = e.getX();
            startY = e.getY();
          }

          finishX = e.getX();
          finishY = e.getY();

          //draw shape
          // model.drawShape(startX, startY, finishX, finishY);
          canvas.repaint();
          //model.undo ?

        }
      });
    }


    /**
     * Re-draws canvas. Called by model whenever a value updates.
     * @param event  Event
     */
    public void propertyChange(PropertyChangeEvent event) {
      canvas.setShapeList((ArrayList<Shape>) event.getNewValue());
      canvas.repaint();

    }

    /**
     * Single listner to check source of button press and decide what to do.
     * @param e  Event
     */
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == colourButton) {
        changeColour();
      } else if (e.getSource() == clearButton) {
        model.clearShapeList();
      } else if (e.getSource() == undoButton) {
        model.undoShapeList();
      } else if (e.getSource() == redoButton) {
        model.redoShapeList();
      } else if (e.getSource() == moveButton) {

      } else if (e.getSource() == lineButton) {
        changeShape("Line");
      } else if (e.getSource() == rectangleButton) {
        changeShape("Rectangle");
      } else if (e.getSource() == parallelogramButton) {
        changeShape("Parallelogram");
      } else if (e.getSource() == triangleButton) {
        changeShape("Triangle");
      } else if (e.getSource() == crossButton) {
        changeShape("Cross");
      } else if (e.getSource() == ellipseButton) {
        changeShape("Ellipse");
      } else if (e.getSource() == murrayPolygonButton) {
        changeShape("Murray Polygon");
      }

    }

    /**
     * Called when the colour button is pressed. Creates a new window with a
     * colour chooser to allow the user to pick a colour. Output label is then
     * changed to reflect this.
     */
    private void changeColour() {

      // Create new window
      JFrame colourFrame = new JFrame("Colour Chooser");
      colourFrame.setSize(DEFAULT__COLOUR_FRAME_WIDTH, DEFAULT_COLOUR_FRAME_HEIGHT);
      colourFrame.setResizable(false);
      colourFrame.setVisible(true);

      // Create panel with label and colour chooser inside
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();
      panel.setLayout(layout);
      final JLabel colorLabel = new JLabel("Please pick a colour");
      final JColorChooser colorChooser = new JColorChooser();

      // Listen for change in the selected colour
      colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
            Color colour = colorChooser.getColor(); // get colour

            // Change output label to reflect new colour
            colorLabel.setForeground(colour);
            selectShapeLabel.setForeground(colour);
            selectShapeLabel.setBorder(BorderFactory.createLineBorder(colour));

            // Change model
            model.setColour(colour);

         }
      });

      // Add components to each other and finally to frame
      panel.add(colorLabel);
      panel.add(colorChooser);
      colourFrame.getContentPane().add(panel, BorderLayout.CENTER);

    }

    /**
     * Called when a shape button is pressed to change the shape drawn and the
     * output label.
     * @param shape  Name of new shape
     */
    private void changeShape(String shape) {
      selectShapeLabel.setText(shape);
      model.setShape(shape);

    }

    /**
     * Instantiates and adds all toolbar buttons to the toolbar.
     */
    private void addToolbar() {

      jtb.setFloatable(false);

      colourButton = new JButton("Colour");
      clearButton = new JButton("Clear");
      undoButton = new JButton("Undo");
      redoButton = new JButton("Redo");
      moveButton = new JButton("Move");
      lineButton = new JButton("Line");
      rectangleButton = new JButton("Rectangle");
      parallelogramButton = new JButton("Parallelogram");
      triangleButton = new JButton("Triangle");
      crossButton = new JButton("Cross");
      ellipseButton = new JButton("Ellipse");
      murrayPolygonButton = new JButton("Murray Polygon");

      selectShapeLabel = new JLabel("Line",SwingConstants.CENTER);
      selectShapeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      selectShapeLabel.setPreferredSize(new Dimension(250, 10));

      jtb.add(selectShapeLabel);
      jtb.add(colourButton);
      jtb.add(clearButton);
      jtb.add(undoButton);
      jtb.add(redoButton);
      jtb.add(moveButton);
      jtb.add(lineButton);
      jtb.add(rectangleButton);
      jtb.add(parallelogramButton);
      jtb.add(triangleButton);
      jtb.add(crossButton);
      jtb.add(ellipseButton);
      jtb.add(murrayPolygonButton);

    }

    /**
     * Instantiates and adds the menu bar.
     */
    private void addMenuBar() {

      JMenu file = new JMenu("File");
      JMenuItem save = new JMenuItem("Save");
      JMenuItem load = new JMenuItem("Load");

      file.add(save);
      file.add(load);
      jmb.add(file);

      load.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "Load not implemented :(");
        }
      });

      save.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "Save not implemented :(");
        }
      });

    }
}
