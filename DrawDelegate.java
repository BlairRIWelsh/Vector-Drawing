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
import javax.swing.border.LineBorder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Graphics;
import java.lang.Double;
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Delegate component of Model-Delegate Paradigm.
 */
public class DrawDelegate implements PropertyChangeListener, ActionListener {

  private DrawModel model; // Link to model component

  private static int DEFAULT_FRAME_WIDTH = 750; // Width of frame
  private static int DEFAULT_FRAME_HEIGHT = 750; // Height of frame
  private static int DEFAULT__COLOUR_FRAME_WIDTH = 500; // Width of colour picker frame
  private static int DEFAULT_COLOUR_FRAME_HEIGHT = 450; // Height of colour picker frame

  // Swing components
  private JFrame mainFrame;
  private JMenuBar jmb;
  private Canvas canvas;
  private JToolBar jtb;
  private JComboBox shapeComboBox;
  private JCheckBox fillBoolean;
  private JLabel colourLabel, shapeLabel, fillLabel, strokeLabel;
  private JSpinner strokeSize;
  private JButton colourButton, clearButton, undoButton, redoButton;
  private JMenu file;
  private JMenuItem save, load, export;
  private JPanel clearAndUndoPanel, drawingOptionsPanel, shapeSelectorPanel, strokePanel, colourPanel, fillPanel, shapePanel;
  private JButton lineButton, rectangleButton, parallelogramButton, triangleButton, crossButton,
  ellipseButton, murrayPolygonButton;

  Color selectedColour; // Currently selected colour

  // Coordinates of mouse presses
  private int startX = -1;
  private int startY = -1;
  private int finishX = -1;
  private int finishY = -1;

  /**
   * Constructor for DrawDelegate class
   * @param model  link to model component
   */
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

        // Add listeners
        addActionListenerForCanvas(this);
        addActionListenerForButtons(this);
        addActionListenerForDrawingOptions();
        addActionListenerForMenuBar();
        model.addListener(this);

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
   * Instantiates and adds all toolbar buttons to the toolbar.
   */
  private void addToolbar() {

    jtb.setFloatable(false);

    // Clear, Undo, and Redo buttons
    clearAndUndoPanel = new JPanel();
    clearButton = new JButton("Clear");
    undoButton = new JButton("Undo");
    redoButton = new JButton("Redo");
    clearAndUndoPanel.add(clearButton);
    clearAndUndoPanel.add(undoButton);
    clearAndUndoPanel.add(redoButton);
    jtb.add(clearAndUndoPanel);

    drawingOptionsPanel = new JPanel();

    // Stroke selector
    strokePanel = new JPanel();
    shapeLabel = new JLabel("Stroke:");
    strokePanel.add(shapeLabel);
    SpinnerModel model = new SpinnerNumberModel(3, 0.5, 100, 0.5); // Initial value, min, max, step
    strokeSize = new JSpinner(model);
    strokePanel.add(strokeSize);
    drawingOptionsPanel.add(strokePanel);

    // Colour label and button
    colourPanel = new JPanel();
    colourLabel = new JLabel("Colour:");
    colourButton = new JButton("    ");
    colourButton.setBackground(Color.BLACK);
    colourButton.setForeground(Color.BLACK);
    colourButton.setBorder(new LineBorder(Color.BLACK));
    colourButton.setOpaque(true);
    colourPanel.add(colourLabel);
    colourPanel.add(colourButton);
    drawingOptionsPanel.add(colourPanel);

    // Fill label and toggle button
    fillPanel = new JPanel();
    fillLabel = new JLabel("Fill:");
    fillPanel.add(fillLabel);
    fillBoolean = new JCheckBox();
    fillPanel.add(fillBoolean);
    drawingOptionsPanel.add(fillPanel);

    jtb.add(drawingOptionsPanel);

    // Choose shape combo box
    shapeSelectorPanel = new JPanel();
    shapeLabel = new JLabel("Shape:");
    shapeComboBox = new JComboBox();
    shapeComboBox.addItem("Line");
    shapeComboBox.addItem("Circle");
    shapeComboBox.addItem("Ellipse");
    shapeComboBox.addItem("Triangle");
    shapeComboBox.addItem("Square");
    shapeComboBox.addItem("Rectangle");
    shapeComboBox.addItem("Parallelogram");
    shapeComboBox.addItem("Pentagon");
    shapeComboBox.addItem("Hexagon");
    shapeComboBox.addItem("Octagon");
    shapeComboBox.addItem("Cross");
    shapeSelectorPanel.add(shapeLabel);
    shapeSelectorPanel.add(shapeComboBox);
    jtb.add(shapeSelectorPanel);

    lineButton = new JButton("Line");
    rectangleButton = new JButton("Rectangle");
    ellipseButton = new JButton("Ellipse");
    crossButton = new JButton("Cross");
    triangleButton = new JButton("Triangle");
    parallelogramButton = new JButton("Parallelogram");
    murrayPolygonButton = new JButton("Murray Polygon");
    lineButton.setVisible(false);
    rectangleButton.setVisible(false);
    ellipseButton.setVisible(false);
    crossButton.setVisible(false);
    triangleButton.setVisible(false);
    parallelogramButton.setVisible(false);
    murrayPolygonButton.setVisible(false);
    jtb.add(lineButton);
    jtb.add(rectangleButton);
    jtb.add(ellipseButton);
    jtb.add(crossButton);
    jtb.add(triangleButton);
    jtb.add(parallelogramButton);
    jtb.add(murrayPolygonButton);

  }

  /**
   * Instantiates and adds the menu bar.
   */
  private void addMenuBar() {

    file = new JMenu("File");
    save = new JMenuItem("Save");
    load = new JMenuItem("Load");
    export = new JMenuItem("Export");

    file.add(save);
    file.add(load);
    file.add(export);
    jmb.add(file);

  }

  /**
   * Adds the action listner to all buttons in the toolbar.
   * @param al  Action listner
   */
  public void addActionListenerForButtons(ActionListener al) {

      clearButton.addActionListener(al);
      undoButton.addActionListener(al);
      redoButton.addActionListener(al);
      colourButton.addActionListener(al);
      shapeComboBox.addActionListener(al);

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

    // Mouse released action listener
    canvas.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {

        if (startX != -1 && startY != -1) {

          model.undoShapeList();

          finishX = e.getX();
          finishY = e.getY();

          // draw shape
          model.drawShape(startX, startY, finishX, finishY);
          canvas.repaint();

          // reset coords
          startX = -1;
          startY = -1;
        }
      }
    });

    // Mouse dragged action listener
    canvas.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {

        if (startX == -1 && startY == -1) {
          startX = e.getX();
          startY = e.getY();
        } else {
          model.undoShapeList();
        }

        finishX = e.getX();
        finishY = e.getY();

        // // Draw shape dynamically as it is dragged
        model.drawShape(startX, startY, finishX, finishY);
        canvas.repaint();
      }
    });

  }

  /**
   * Adds the action listner for the fill tick box and stroke size.
   */
  private void addActionListenerForDrawingOptions() {

    fillBoolean.addItemListener(new ItemListener() {
       public void itemStateChanged(ItemEvent e) {
          model.switchFillToggle(); // Switch toggle when checked or uncheked
       }
    });

    strokeSize.addChangeListener(new ChangeListener() {
       public void stateChanged(ChangeEvent e) {
          Double db = new Double((double)strokeSize.getValue());
          model.setStrokeSize((float)db.floatValue()); // Change stroke size
       }
    });

  }

  /**
   * Adds action listener for the menu bar.
   */
  private void addActionListenerForMenuBar() {

    // For loading files
    load.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // Open file chooser
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

          // Select file and load the new shape list
          File file = fc.getSelectedFile();
          model.loadShapeList(file);
          JOptionPane.showMessageDialog(null, "File loaded: " + file.getName());
        }
      }
    });

    // Save the current file
    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // Open file chooser
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(null);

        if(option == JFileChooser.APPROVE_OPTION){

            // Select file and save the shape list
            File file = fileChooser.getSelectedFile();
            model.saveShapeList(file);
            JOptionPane.showMessageDialog(null, "Image saved as " + file.getName() + ".txt");
         }
      }
    });

    // Export the current file as a .png image
    export.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // Load as image
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        canvas.paint(g);
        g.dispose();

        // Open file chooser
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Image", "png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(canvas);

        if(option == JFileChooser.APPROVE_OPTION){
           try {

             // Export image as .png
             ImageIO.write(image, "png", fileChooser.getSelectedFile());
             JOptionPane.showMessageDialog(null, "Image exported as " + fileChooser.getSelectedFile().getName() + ".png");

           } catch (Exception ex) {
             System.out.println("Export failed");
             ex.printStackTrace();
           }
        }
      }
    });

  }

  /**
   * Single listner to check source of button press and decide what to do.
   * @param e  Event
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == clearButton) {
      model.clearShapeList();
    } else if (e.getSource() == undoButton) {
      model.undoShapeList();
    } else if (e.getSource() == redoButton) {
      model.redoShapeList();
    } else if (e.getSource() == colourButton) {
      pickColour();
    } else if (e.getSource() == shapeComboBox) {
      model.setShape((String)shapeComboBox.getSelectedItem());
    } else if (e.getSource() == strokeSize) {
      model.setStrokeSize((float)strokeSize.getValue());
    }

  }

  /**
   * Called when the colour button is pressed. Creates a new window with a
   * colour chooser to allow the user to pick a colour. Output label is then
   * changed to reflect this.
   */
  private void pickColour() {

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
          colorLabel.setForeground(colour); // Change output label to reflect new colour
          colourButton.setBackground(colour); // Change colour button to reflect new colour
          model.setColour(colour); // Set model queue
       }
    });

    // Add components to each other and finally to frame
    panel.add(colorLabel);
    panel.add(colorChooser);
    colourFrame.getContentPane().add(panel, BorderLayout.CENTER);

  }

}
