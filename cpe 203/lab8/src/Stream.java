import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Stream
{
    public static void main(String[] args)
    {
        //Array to hold data that is grabbed.
        List<Point> plist = new ArrayList<>();
        //Function to convert all data in input file into Points to collect into list.
        Scanner(plist);


        //filter z's.
        List<Point> z_val =
                plist.stream()
                        .filter( s -> s.getZ() <= 2.0)
                        .collect(Collectors.toList());



        //Scale points down by 0.5
        List<Point> scaled =
                z_val.stream()
                    .map(s -> new Point(s.getX() * 0.5,
                                        s.getY() * 0.5,
                                        (double)s.getZ() * 0.5))
                    .collect(Collectors.toList());

        //translate by {-150,-37}
        List<Point> translate =
                scaled.stream()
                    .map(s -> new Point(s.getX() - 150,
                                        s.getY() - 37,
                                        s.getZ() - 0)) //I just subtracted zero since spacing bothered me :I
                    .collect(Collectors.toList());

        //Write newly created point list into file.

        Writer(translate);
    }

    public static void Scanner(List<Point> lst)
    {
        try
        {
            File input = new File("positions.txt");
            Scanner sc = new Scanner(input);

            while (sc.hasNextLine())
            {

                String line = sc.nextLine();
                String [] nums = line.split(",");

                lst.add(new Point(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]), Double.parseDouble(nums[2])));
            }
        }
        catch (Exception e){ System.out.println(e.getMessage()); }
    }

    public static void Writer(List<Point> lst)
    {
        try
        {
            PrintStream output = new PrintStream("drawMe.txt");
            for(Point data : lst)
            {
                output.println(data.getX() + ", " + data.getY() + ", " + data.getZ());
            }
        }
        catch (Exception e) { System.out.println("Can't Write"); }
    }

}
