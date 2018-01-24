class SimpleLoop
{
   public static int sum(int low, int high)
   {
   	int total = 0;
   	while (low <= high){
   		total = total + low;
   		low++;

   	}
      /* TO DO:  Return the sum of the integers between
         low and high (inclusive).  Yes, this can be
         done without a loop, but the point is to
         practice the syntax for a loop.
      */

      return total;
   }


   public static void main(String[] args)
   {
      int test = sum(1,9);
      System.out.println(test);
   }

}

