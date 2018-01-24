import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /* some sample tests but you must write more! see lab write up */

   // Circle Tests

   @Test
   public void testCircleGetColor() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(Color.BLACK, c.getColor());
   }

   @Test
   public void testCircleSetColor() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      c.setColor(Color.BLUE);
      assertEquals(Color.BLUE, c.getColor());
   }

   @Test
   public void testCircleGetArea()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);
   }

   @Test
   public void testCircleGetPerimeter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }

   @Test
   public void testCircleTranslate() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      c.translate(new Point(3, 4));
      assertEquals(new Point(5, 7), c.getCenter());

   }

   @Test
   public void testCircleGetRadius() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertEquals(5.678, c.getRadius(), DELTA);
   }

   @Test
   public void testCircleSetRadius() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      c.setRadius(3.2);
      assertEquals(3.2, c.getRadius(), DELTA);
   }

   @Test
   public void testCircleGetCenter() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertEquals(new Point(2, 3), c.getCenter());
   }

   // Rectangle Tests
   @Test
   public void testRectGetColor() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(Color.MAGENTA, r.getColor());
   }

   @Test
   public void testRectSetColor() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      r.setColor(Color.BLACK);
      assertEquals(Color.BLACK, r.getColor());
   }

   @Test
   public void testRectGetArea() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(8.0, r.getArea(), DELTA);

   }

   @Test
   public void testRectGetPerimeter() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(12.0, r.getPerimeter(), DELTA);
   }

   @Test
   public void testRectTranslate() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      r.translate(new Point(5, 2));
      assertEquals(new Point(5, 6), r.getTopLeft());
   }

   @Test
   public void testRectGetWidth() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(2.0, r.getWidth(), DELTA);

   }

   @Test
   public void testRectSetWidth() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      r.setWidth(4.2);
      assertEquals(4.2, r.getWidth(), DELTA);
   }

   @Test
   public void testRectGetHeight() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(4.0, r.getHeight(), DELTA);
   }

   @Test
   public void testRectSetHeight() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      r.setHeight(1.2);
      assertEquals(1.2, r.getHeight(), DELTA);
   }

   @Test
   public void testRectGetTopLeft() {
      Rectangle r = new Rectangle(2.0, 4.0, new Point(0, 4), Color.MAGENTA);
      assertEquals(new Point(0, 4), r.getTopLeft());
   }

   @Test
   public void testTriangleGetColor() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(Color.BLACK, t.getColor());
   }

   @Test
   public void testTriangleSetColor() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      t.setColor(Color.RED);
      assertEquals(Color.RED, t.getColor());
   }

   @Test
   public void testTriangleGetArea() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(8.0, t.getArea(), DELTA);
   }

   @Test
   public void testTriangleGetPerimeter() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(13.65685, t.getPerimeter(), DELTA);
   }

   @Test
   public void testTriangleTranslate() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      t.translate(new Point(3, 0));
      assertEquals(new Point(3, 0), ((Triangle) t).getVertexA());
      assertEquals(new Point(3, 4), ((Triangle) t).getVertexB());
      assertEquals(new Point(7, 0), ((Triangle) t).getVertexC());
   }

   @Test
   public void testTriangleGetVertexA() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(new Point(0, 0), ((Triangle) t).getVertexA());
   }

   @Test
   public void testTriangleGetVertexB() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(new Point(0, 4), ((Triangle) t).getVertexB());
   }

   @Test
   public void testTriangleGetVertexC() {
      Shape t = new Triangle(new Point(0, 0), new Point(0, 4), new Point(4, 0), Color.BLACK);
      assertEquals(new Point(4, 0), ((Triangle) t).getVertexC());
   }

   @Test
   public void testPolygonGetColor() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      assertEquals(Color.GRAY, cP.getColor());
   }

   @Test
   public void testPolygonSetColor() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      cP.setColor(Color.BLACK);
      assertEquals(Color.BLACK, cP.getColor());
   }

   @Test
   public void testPolygonGetArea() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      assertEquals(4.0, cP.getArea(), DELTA);
   }

   @Test
   public void testPolygonGetPerimeter() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      assertEquals(8.0, cP.getPerimeter(), DELTA);
   }

   @Test
   public void testPolygonTranslate() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      cP.translate(new Point(1, 0));
      assertEquals(new Point(1, 0), cP.getVertex(0));
      assertEquals(new Point(3, 0), cP.getVertex(1));
      assertEquals(new Point(3, 2), cP.getVertex(2));
      assertEquals(new Point(1, 2), cP.getVertex(3));
   }

   @Test
   public void testPolygonGetVertex() {
      Point[] polygonArray = {new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)};
      ConvexPolygon cP = new ConvexPolygon(polygonArray, Color.GRAY);
      assertEquals(new Point(0, 0), cP.getVertex(0));
   }
   @Test
   public void testWorkSpaceAreaOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), 
                 Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test
   public void testWorkSpaceGetCircles()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getCircles());
   }


   @Test
   public void testWorkspaceAdd() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      wk.add(c1);
      assertEquals(c1, wk.get(0));
   }

   @Test
   public void testWorkspaceGet() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.GRAY);
      wk.add(c1);
      wk.add(r1);
      assertEquals(r1, wk.get(1));

   }

   @Test
   public void testWorkspaceSize() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.GRAY);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      Point[] vertexArray = {new Point(0, 0), new Point(2, 5), new Point(5, 0)};
      ConvexPolygon cP1 = new ConvexPolygon(vertexArray, Color.MAGENTA);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      wk.add(cP1);
      assertEquals(4, wk.size());

   }

   @Test
   public void testWorkspaceGetRect() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.GRAY);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      Point[] vertexArray = {new Point(0, 0), new Point(2, 5), new Point(5, 0)};
      ConvexPolygon cP1 = new ConvexPolygon(vertexArray, Color.MAGENTA);
      Rectangle r2 = new Rectangle(1.2, 2.2, new Point(3, 1), Color.GRAY);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      wk.add(cP1);
      wk.add(r2);
      List<Shape> expected = new LinkedList<>();
      expected.add(r1);
      expected.add(r2);
      assertEquals(expected, wk.getRectangles());
   }

   @Test
   public void testWorkspaceGetTriangle() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.GRAY);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      Point[] vertexArray = {new Point(0, 0), new Point(2, 5), new Point(5, 0)};
      ConvexPolygon cP1 = new ConvexPolygon(vertexArray, Color.MAGENTA);
      Rectangle r2 = new Rectangle(1.2, 2.2, new Point(3, 1), Color.GRAY);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      wk.add(cP1);
      wk.add(r2);
      List<Shape> expected = new LinkedList<>();
      expected.add(t1);
      assertEquals(expected, wk.getTriangles());
   }

   @Test
   public void testWorkspaceGetPolygons() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.GRAY);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      Point[] vertexArray = {new Point(0, 0), new Point(2, 5), new Point(5, 0)};
      ConvexPolygon cP1 = new ConvexPolygon(vertexArray, Color.MAGENTA);
      Rectangle r2 = new Rectangle(1.2, 2.2, new Point(3, 1), Color.GRAY);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      wk.add(cP1);
      wk.add(r2);
      List<Shape> expected = new LinkedList<>();
      expected.add(cP1);
      assertEquals(expected, wk.getConvexPolygons());
   }

   @Test
   public void testWorkspaceGetShapesByColor() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.BLACK);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      Point[] vertexArray = {new Point(0, 0), new Point(2, 5), new Point(5, 0)};
      ConvexPolygon cP1 = new ConvexPolygon(vertexArray, Color.BLACK);
      Rectangle r2 = new Rectangle(1.2, 2.2, new Point(3, 1), Color.GRAY);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      wk.add(cP1);
      wk.add(r2);
      List<Shape> expected = new LinkedList<>();
      expected.add(c1);
      expected.add(r1);
      expected.add(cP1);
      assertEquals(expected, wk.getShapesByColor(Color.BLACK));
   }

   @Test
   public void testWorkspaceGetPerimeterShapes() {
      WorkSpace wk = new WorkSpace();
      Shape c1 = new Circle(2.2, new Point(0, 0), Color.BLACK);
      Shape r1 = new Rectangle(2.0, 1.0, new Point(0, 0), Color.BLACK);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GRAY);
      double expected = 2 * Math.PI * 2.2 + 6.0 + 1 + 1 + Math.pow(2.0, 0.5);
      wk.add(c1);
      wk.add(r1);
      wk.add(t1);
      assertEquals(expected, wk.getPerimeterOfAllShapes(), DELTA);

   }
   /* HINT - comment out implementation tests for the classes that you have not 
    * yet implemented */
   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testConvexPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertex");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[] {int.class});

      verifyImplSpecifics(ConvexPolygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
