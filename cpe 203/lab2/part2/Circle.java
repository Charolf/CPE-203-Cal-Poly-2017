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

	//perimeter
	public double perimeter(){ return 2*Math.PI*radius; }

}