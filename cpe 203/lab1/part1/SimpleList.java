import java.util.List;
import java.util.LinkedList;
import java.lang.*;
import java.util.*;

class SimpleList
{
   public static List<Integer> squareAll(List<Integer> values)
   {
      int i=0;
      List<Integer> newValues = new LinkedList<Integer>();

      for (int val : values){
         newValues.add((int)Math.pow(val,2));
         System.out.print(newValues.get(i) + " ");
         i++;
      }

      /* TO DO: The output list, newValues, should hold as
         its elements the square of the corresponding element
         in the input list.

         Write a loop to add the square of each element from the
         input list into the output list.  Use a "foreach".
      */

      return newValues;
   }

   /*
   public static void main(String[] args)
   {
      List<Integer> test = Arrays.asList(1,2,3,4);
      List<Integer> func = squareAll(test);
   }
   */



}
