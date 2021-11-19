import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

public class TestJunit {

  private DrawModel drawModel;

  @Before
   public void setup() {
       drawModel = new drawModel();
   }

   ///
   ///
   @Test
    public void testModelExists() {
        assertNotNull(drawModel);
    }

   @Test
   public void testSetShape() {
     String shape = 'Rectangle';
     drawModel.testSetShape(shape);
     assertEquals(drawModel.getShape(), shape);
   }

   @Test
   public void testSetColour() {
     Color colour = Color.RED;
     drawModel.testSetColour(colour);
     assertEquals(drawModel.getColour(), colour);
   }

   @Test
   public void testSetStrokeSize() {
     float strokeSize = 5.5
     drawModel.testSetStrokeSize(strokeSize);
     assertEquals(drawModel.getStrokeSize(), strokeSize);
   }

   @Test
   public void testFillToggle() {
     boolean fillToggle = true;
     drawModel.testSetFillToggle(fillToggle);
     assertEquals(drawModel.getFillToggle(), fillToggle);
   }

   @Test
   public void testDrawShapes() {

     int startX = 0;
     int startY = 0;
     int finishX = 100;
     int finishY = 100;

     // Create a new shape list and add an ellipse to it
     ArrayList<Shape> shapeList = new ArrayList<Shape>();
     shapeList.add(new Line(startX,startY,finishX,finishY));
     shapeList.add(new Rectangle(startX,startY,finishX,finishY));
     shapeList.add(new Parallelogram(startX,startY,finishX,finishY));
     shapeList.add(new Triangle(startX,startY,finishX,finishY));
     shapeList.add(new Cross(startX,startY,finishX,finishY));
     shapeList.add(new Ellipse(startX,startY,finishX,finishY));
     shapeList.add(new Pentagon(startX,startY,finishX,finishY));
     shapeList.add(new Hexagon(startX,startY,finishX,finishY));
     shapeList.add(new Octagon(startX,startY,finishX,finishY));
     shapeList.add(new Square(startX,startY,finishX,finishY));
     shapeList.add(new Circle(startX,startY,finishX,finishY));

     drawModel.setShape("Line");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Rectangle");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Parallelogram");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Triangle");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Cross");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Ellipse");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Pentagon");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Hexagon");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Octagon");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Square");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Circle");
     drawModel.drawShape(startX,startY,finishX,finishY);

     assertEquals(drawModel.getShapeList(), shapeList);
   }

   @Test
   public void testClearShapeList() {

     int startX = 0;
     int startY = 0;
     int finishX = 100;
     int finishY = 100;

     // Create a new empty shape list
     ArrayList<Shape> shapeList = new ArrayList<Shape>();

     // Draw 3 shapes on the model
     drawModel.setShape("Line");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Rectangle");
     drawModel.drawShape(startX,startY,finishX,finishY);
     drawModel.setShape("Parallelogram");
     drawModel.drawShape(startX,startY,finishX,finishY);

     // Clear the DrawModel
     drawModel.clearShapeList();

     assertEquals(drawModel.getShapeList(), shapeList);

   }

   @Test
   public void testPrintShapeList() {

   }

   @Test
   public void testUndoShapeList() {

   }

   @Test
   public void redoShapeList() {

   }

   @Test
   public void testUpdate() {

   }

   @Test
   public void testSaveShapeList() {

   }

   @Test
   public void testLoadShapeList() {

   }

}
