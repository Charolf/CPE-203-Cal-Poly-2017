import java.awt.*;

public class ConvexPolygon implements Shape
{
	private Point[] vertices;
	private Color color;

	public ConvexPolygon(Point[] vertices, Color color)
	{
		this.vertices = vertices;
		this.color = color;
	}

	//default
	public Point getVertex(int index) { return vertices[index]; }

	//interface
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getArea()
	{	
		double result = 0;
		for(int i=0; i < vertices.length-1; i++)
		{
			Point NextPoint = vertices[i+1];
			if (i == vertices.length-1)
			{
				NextPoint = vertices[0];
			}
			result += ( vertices[i].getX()*NextPoint.getY() ) - ( NextPoint.getX()*vertices[i].getY() );
		}
		return 0.5*result;
	}

	public void translate(Point point)
	{
		for(int i=0; i < vertices.length; i++)
		{
			vertices[i].setLocation( vertices[i].getX() + point.getX() , vertices[i].getY() + point.getY() );
		}
	}

	public double getPerimeter()
	{	
		double result = 0;
		for(int i=0; i < vertices.length; i++)
		{
			Point NextPoint = null;
			if (i == vertices.length-1)
			{
				NextPoint = vertices[0];
			}
			else 
			{
				NextPoint = vertices[i+1];
			}
			double func = Math.pow( Math.pow(NextPoint.getX() - vertices[i].getX(), 2) + Math.pow(NextPoint.getY() - vertices[i].getY(), 2) , 0.5);
			result += func;
			System.out.println(vertices.length);
		}
		return result;
	}

}