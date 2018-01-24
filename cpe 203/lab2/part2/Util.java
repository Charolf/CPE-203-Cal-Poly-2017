public class Util
{


	public static double perimeter(Circle input1)
	{
		return 2*Math.PI*input1.getRadius();
	}


	public static double perimeter(Rectangle input3)
	{
		Point TL = input3.getTopLeft();
		Point BR = input3.getBottomRight();

		return (2*(BR.getX() - TL.getX()) ) + (2*(TL.getY() - BR.getY()));
	}


	public static double perimeter(Polygon input2)
	{
		Point next;
		double perimeter = 0;

		for (int i = 0; i < input2.getPoints().size(); i++)
		{

			//current index.
			Point current = input2.getPoints().get(i);

			//going through  indexes.
			if (i < input2.getPoints().size() -1)
			{
				next = input2.getPoints().get(i+1);
			}
			else
			{
				//return point or closing of poly.
				next = input2.getPoints().get(0);
			}

			//distance formula being applied. EX: distance of i0 & i1, then i1 & i2, etc.
			double distance = Math.pow( Math.pow(next.getX() - current.getY(), 2) + Math.pow(next.getY() - current.getX(), 2), 0.5);

			//accumulationn of all distances (stops when conncected finally).
			perimeter += distance;
		}

		return perimeter;
	}


}