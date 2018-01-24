class Point
{
	private final double x;
	private final double y;

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getRadius()
	{
		return Math.pow( Math.pow(x, 2) + Math.pow(y, 2) , 0.5);
	}

	public double getAngle()
	{
		double ArcTan = Math.atan2(y, x);
		return ArcTan;
	}

	public Point rotate90()
	{
		return new Point(-y, x);
	}

}