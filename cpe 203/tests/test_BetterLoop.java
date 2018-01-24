class test_BetterLoop
{
   public static void main(String[] args)
   {
      int[] array = {1,2,3,4};
      boolean tester = contains(array, 5);
      System.out.println(tester);
   }


   public static boolean contains(int [] values, int v)
   {
      boolean answer = false;
      for (int val : values)
      {
         if (val == v)
            answer = true;
      }

      /* TO DO: if value v is in the array, return true.
         If not, return false.  Use a "foreach" loop.
      */
      return answer;
   }


}
