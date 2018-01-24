import java.util.*;

public class Bigger
{
	public static double whichIsBigger(Rectangle input1, Circle input2, Polygon input3)
	{
		double p1 = input1.perimeter();
		double p2 = input2.perimeter();
		double p3 = input3.perimeter();

		ArrayList<Double> lst = new ArrayList<Double>();
		lst.add(p1);
		lst.add(p2);
		lst.add(p3);

		double answer = Collections.max(lst); //Collections part of max in system.

		return answer;

	}
}