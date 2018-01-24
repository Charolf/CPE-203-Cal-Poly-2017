import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class WorkSpace
{
	private List<Shape> shapes;

	public WorkSpace()
	{
		this.shapes = new ArrayList<>();
	}

	//default
	public void add(Shape shape) { shapes.add(shape); }

	public Shape get(int index) { return shapes.get(index); }
	public int size(){ return shapes.size(); }

	//grabs all circles and puts into list.
	public List<Circle> getCircles()
	{	
		List<Circle> c_list = new ArrayList<>();
		for(Shape i : shapes)
		{
			if(i instanceof Circle)
				//MUST DOWNCAST FROM SHAPE TO CIRCLE.
				c_list.add((Circle)i);
		}
		return c_list;
	}

	//grabs all rectangles and puts into list.
	public List<Rectangle> getRectangles()
	{	
		List<Rectangle> r_list = new ArrayList<>();
		for(Shape i : shapes)
		{
			if(i instanceof Rectangle)
				//MUST DOWNCAST FROM SHAPE TO RECTANGLE.
				r_list.add((Rectangle)i);
		}
		return r_list;
	}

	//grabs all triangles and puts into list.
	public List<Triangle> getTriangles()
	{	
		List<Triangle> t_list = new ArrayList<>();
		for(Shape i : shapes)
		{
			if(i instanceof Triangle)
				//MUST DOWNCAST FROM SHAPE TO TRIANGLE.
				t_list.add((Triangle)i);
		}
		return t_list;
	}

	//grabs all ConvexPolygons and puts into list.
	public List<ConvexPolygon> getConvexPolygons()
	{	
		List<ConvexPolygon> cp_list = new ArrayList<>();
		for(Shape i : shapes)
		{
			if(i instanceof ConvexPolygon)
				//MUST DOWNCAST FROM SHAPE TO CONVEXPOLYGON.
				cp_list.add((ConvexPolygon)i);
		}
		return cp_list;
	}

	//grabs all shapes with same color and puts into list.
	public List<Shape> getShapesByColor(Color color)
	{	
		List<Shape> s_list = new ArrayList<>();
		for(Shape i : shapes)
		{
			if(i.getColor().equals(color))
				s_list.add(i);
		}
		return s_list;
	}

	//gets area of all shapes in list
	public double getAreaOfAllShapes()
	{	
		double result = 0;
		for(Shape i : shapes)
		{
			result += i.getArea();
		}
		return result;
	}

	//gets perimeter of all shapes in list
	public double getPerimeterOfAllShapes()
	{	
		double result = 0;
		for(Shape i : shapes)
		{
			result += i.getPerimeter();
		}
		return result;
	}
}