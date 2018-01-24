import java.awt.Point;
import java.awt.*;

public class Circle implements Shape
{
	private double radius;
	private Point center;
	private Color color;

	public Circle(double radius, Point center, Color color)
	{
		this.radius = radius;
		this.center = center;
		this.color = color;
	}

	//default
	public double getRadius() { return radius; }
	public void setRadius(double r) { this.radius = r; }
	public Point getCenter() { return center; }
	//interface
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }
	public double getArea() { return (Math.PI*Math.pow(radius, 2)); }
	public void translate(Point point) { center.setLocation(center.getX() + point.getX(), center.getY() + point.getY()); }
	public double getPerimeter() { return (2*Math.PI*radius); }
}