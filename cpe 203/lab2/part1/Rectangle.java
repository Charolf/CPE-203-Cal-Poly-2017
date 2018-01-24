public class Rectangle
{
	private final Point top_left;
	private final Point bottom_right;

	public Rectangle(final Point top_left, final Point bottom_right)
	{
		this.top_left = top_left;
		this.bottom_right = bottom_right;
	}

	//Constructors
	public Point getTopLeft(){ return top_left; }
	public Point getBottomRight(){ return bottom_right; }

}

