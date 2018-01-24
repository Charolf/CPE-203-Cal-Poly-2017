import java.util.*;

public class Bigger
{
	public static double whichIsBigger(Rectangle input1, Circle input2, Polygon input3)
	{
		double p1 = Util.perimeter(input1);
		double p2 = Util.perimeter(input2);
		double p3 = Util.perimeter(input3);

		ArrayList<Double> lst = new ArrayList<Double>();
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);

		double answer = Collections.max(lst); //Collections part of max in system.

		return answer;

	}
}