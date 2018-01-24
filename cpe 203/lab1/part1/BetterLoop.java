class BetterLoop
{

	
    /* TO DO: if value v is in the array, return true.
       If not, return false.  Use a "foreach" loop.
    */

   public static boolean contains(int [] values, int v)
   {
      boolean answer = false;
      for (int val : values)
      {
         if (val == v)
            answer = true;
      }
      return answer;
   }


}
