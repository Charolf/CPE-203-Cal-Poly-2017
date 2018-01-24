class Point
{
	private final double x;
	private final double y;
	private final double z;

	public Point(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString()
	{
		return (x + ", " + y + ", " + z);
	}

	public double getX() { return x; }
	public double getY() { return y; }
	public double getZ() { return z; }
}