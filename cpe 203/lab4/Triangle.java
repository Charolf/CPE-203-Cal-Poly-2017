import java.awt.*;

public class Triangle implements Shape
{
	private Point VertexA;
	private Point VertexB;
	private Point VertexC;
	private Color color;

	public Triangle(Point VertexA, Point VertexB, Point VertexC, Color color)
	{
		this.VertexA = VertexA;
		this.VertexB = VertexB;
		this.VertexC = VertexC;
		this.color = color;
	}

	//default
	public Point getVertexA() { return VertexA; }
	public Point getVertexB() { return VertexB; }
	public Point getVertexC() { return VertexC; }

	//inteface
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getArea()
	{
		return Math.abs( (VertexA.getX()*(VertexB.getY()-VertexC.getY()) + 
						VertexB.getX()*(VertexC.getY() - VertexA.getY()) + 
						VertexC.getX()*(VertexA.getY() - VertexB.getY())) / 2);
	}

	public void translate(Point point)
	{
		VertexA.setLocation(VertexA.getX() + point.getX(), VertexA.getY() + point.getY());
		VertexB.setLocation(VertexB.getX() + point.getX(), VertexB.getY() + point.getY());
		VertexC.setLocation(VertexC.getX() + point.getX(), VertexC.getY() + point.getY());
	}

	public double getPerimeter()
	{
		double AB = Math.pow( Math.pow(VertexB.getX() - VertexA.getX(), 2) + Math.pow(VertexB.getY() - VertexA.getY(), 2) , 0.5);
		double BC = Math.pow( Math.pow(VertexC.getX() - VertexB.getX(), 2) + Math.pow(VertexC.getY() - VertexB.getY(), 2) , 0.5);
		double AC = Math.pow( Math.pow(VertexC.getX() - VertexA.getX(), 2) + Math.pow(VertexC.getY() - VertexA.getY(), 2) , 0.5);
		return AB+BC+AC;
	}
}