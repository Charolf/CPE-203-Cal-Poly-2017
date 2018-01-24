import java.awt.*;

public class Rectangle implements Shape
{
	private double width;
	private double height;
	private Point TopLeft;
	private Color color;

	public Rectangle(double width, double height, Point TopLeft, Color color)
	{
		this.width = width;
		this.height = height;
		this.TopLeft = TopLeft;
		this.color = color;
	}
	//default
	public double getWidth() { return width; }
	public void setWidth(double w) { this.width = w; }
	public double getHeight() { return height; }
	public void setHeight(double h) { this.height = h; }
	public Point getTopLeft() { return TopLeft; }
	//interface
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }
	public double getArea() { return (width*height); }
	public void translate(Point point) { TopLeft.setLocation(TopLeft.getX() + point.getX(), TopLeft.getY() + point.getY()); }
	public double getPerimeter() { return ( (2*width) + (2*height) ); }
}