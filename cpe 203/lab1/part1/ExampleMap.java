import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;

class ExampleMap
{
   public static List<String> highEnrollmentStudents(Map<String, List<Course>> courseListsByStudentName, int unitThreshold)
   {
      List<String> overEnrolledStudents = new LinkedList<>();

      /*
         Build a list of the names of students currently enrolled
         in a number of units strictly greater than the unitThreshold.
      */

      //This just grabs the keys from courselist... which are just a giant list of strings. Like Hash all have special keys.
      Set<String> keygrabber = courseListsByStudentName.keySet();

      for (String keys : keygrabber){

         //This grabs actual values from the list. 
         List<Course> ActualValues = courseListsByStudentName.get(keys);

         //This goes through Actual Values and adds up student units.
         int units = 0;
         for (Course val : ActualValues){
            units += val.getNumUnits(); //grabs all units and adds.
         }

         if (units > unitThreshold){ //If current student's total units greater than threshhold, add their key into overEnrolledStudents.
            overEnrolledStudents.add(keys);
         }

      }

      return overEnrolledStudents;      
   }

   /*
   public static void main(String[] args)
   {
      Map<String, List<Course>> courseMap = new HashMap<String, List<Course>>();
      Course[] courseListDerek = {new Course("stats", 4), new Course("phys", 4), new Course("cpe", 4)};
      Course[] courseListBrian = {new Course("fuck", 20)};
      courseMap.put("derek", Arrays.asList(courseListDerek));
      courseMap.put("brian", Arrays.asList(courseListBrian));
      List<String> test1 = highEnrollmentStudents(courseMap, 3);
      System.out.println(Arrays.toString(test1.toArray()));
   }
   */

}

