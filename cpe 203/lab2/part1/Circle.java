public class Circle
{
	private final Point point;
	private final double radius;

	public Circle(Point point, double radius)
	{
		this.point = point;
		this.radius = radius;
	}

	//Constructors
	public Point getCenter(){ return point; }
	public double getRadius(){ return radius; }

}

public class Circle
{
	private final Point point;
	private final double radius;

	public Circle(Point point, double radius)
	{
		this.point = point;
		this.radius = radius;
	}

	//Constructors
	public static Point getCenter(Circle c){ return c.point; }
	public double getRadius(Circle c){ return c.radius; }

}

