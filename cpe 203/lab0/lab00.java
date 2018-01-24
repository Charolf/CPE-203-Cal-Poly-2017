public class Lab00
{


   public static void main(String[] args)
   {

   	//declaring and initializing some variables
   	int x = 5;
   	String y = "hello";
   	double z = 9.8;

   	//printing the variables
   	System.out.println(" x:  " + x + "  y:  " + y + "  z:  " + z);

   	//a list (make an array in java)
   	int[] nums = {3,6,-1,2};
   	for (int i = 0; i<4; i++)
   		System.out.println(nums[i]);

   	//call a function
   	String numfound = char_count(y, 'l');
   	System.out.println("Found: " + numfound);

      //a counting for loop
      for (int a = 1; a < 11; a++)
         System.out.print(a + " ");

   	System.out.println();

   }


   public static String char_count(String s, char n)
   {

   	int count = 0;
   	for (int i = 0; i< s.length(); i++)
   	{
   		if (s.charAt(i) == n)
   		{
   			count++;
   		}
   	}
   	return Integer.toString(count);

   }


}