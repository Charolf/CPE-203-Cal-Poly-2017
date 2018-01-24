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

}
