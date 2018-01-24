import java.util.List;

public class Polygon
{	
	private final List<Point> ListOfPoints;

	public Polygon(List<Point> ListOfPoints)
	{
		this.ListOfPoints = ListOfPoints;
	}

	//Consrtructors
	public List<Point> getPoints(){ return ListOfPoints; }

	//perimeter
	public double perimeter()
	{
		Point next;
		double perimeter = 0;

		for (int i = 0; i < ListOfPoints.size(); i++)
		{

			//current index.
			Point current = ListOfPoints.get(i);

			//going through  indexes.
			if (i < ListOfPoints.size() -1)
			{
				next = ListOfPoints.get(i+1);
			}
			else
			{
				//return point or closing of poly.
				next = ListOfPoints.get(0);
			}

			//distance formula being applied. EX: distance of i0 & i1, then i1 & i2, etc.
			double distance = Math.pow( Math.pow(next.getX() - current.getY(), 2) + Math.pow(next.getY() - current.getX(), 2), 0.5);

			//accumulationn of all distances (stops when conncected finally).
			perimeter += distance;
		}

		return perimeter;
	}

}
