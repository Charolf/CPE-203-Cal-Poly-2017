import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.*;

public class LogAnalyzer
{
      //constants to be used when pulling data out of input
      //leave these here and refer to them to pull out values
   private static final String START_TAG = "START";
   private static final int START_NUM_FIELDS = 3;
   private static final int START_SESSION_ID = 1;
   private static final int START_CUSTOMER_ID = 2;
   private static final String BUY_TAG = "BUY";
   private static final int BUY_NUM_FIELDS = 5;
   private static final int BUY_SESSION_ID = 1;
   private static final int BUY_PRODUCT_ID = 2;
   private static final int BUY_PRICE = 3;
   private static final int BUY_QUANTITY = 4;
   private static final String VIEW_TAG = "VIEW";
   private static final int VIEW_NUM_FIELDS = 4;
   private static final int VIEW_SESSION_ID = 1;
   private static final int VIEW_PRODUCT_ID = 2;
   private static final int VIEW_PRICE = 3;
   private static final String END_TAG = "END";
   private static final int END_NUM_FIELDS = 2;
   private static final int END_SESSION_ID = 1;




      //a good example of what you will need to do next
      //creates a map of sessions to customer ids
   private static void processStartEntry(
      final String[] words,
      final Map<String, List<String>> sessionsFromCustomer)
   {
      if (words.length != START_NUM_FIELDS)
      {
         return;
      }

      //check if there already is a list entry in the map
      //for this customer, if not create one
      List<String> sessions = sessionsFromCustomer.get(words[START_CUSTOMER_ID]);


      if (sessions == null)
      {
         sessions = new LinkedList<>();
         sessionsFromCustomer.put(words[START_CUSTOMER_ID], sessions);
      }

         //now that we know there is a list, add the current session
      sessions.add(words[START_SESSION_ID]);
   }




      //similar to processStartEntry, should store relevant view
      //data in a map - model on processStartEntry, but store
      //your data to represent a view in the map (not a list of strings)
   private static void processViewEntry(
      final String[] words,
      final Map<String, List<View>> viewsFromSession)
      /* add parameters as needed */

   {  
      //if it be length of 4 since View is --> VIEW, SESSION, PRODUCT ID, PRICE
      if (words.length != VIEW_NUM_FIELDS)
      {
      //if not it just does nothing and goes to next.
         return;
      }
      else
      {
         //Checks if sessionn already there.
         List<View> views = viewsFromSession.get(words[VIEW_SESSION_ID]);

         //if currently empty when just beginning.
         if (views == null)
         {
            //current linked list that will be added to.
            views = new LinkedList<>();
            //So if that session never existed, it is added in. [Session 1: EMPTY, Session 2: Object1, Object2]
            viewsFromSession.put(words[VIEW_SESSION_ID], views);
         }

         //if that session already existed, just add object in.
         views.add(new View(words[VIEW_SESSION_ID], words[VIEW_PRODUCT_ID], Integer.parseInt(words[VIEW_PRICE]) ));

      }

   }

      //similar to processStartEntry, should store relevant purchases
      //data in a map - model on processStartEntry, but store
      //your data to represent a purchase in the map (not a list of strings)
   private static void processBuyEntry(
      final String[] words,
      final Map<String, List<Buy>> buysFromSession)
      /* add parameters as needed */
      
   {
      //if length 5.
      if (words.length != BUY_NUM_FIELDS)
      {
      //if not it just does nothing and goes to next.
         return;
      }
      else
      {
         //Checks if sessionn already there.
         List<Buy> buys = buysFromSession.get(words[BUY_SESSION_ID]);

         //if currently empty when just beginning.
         if (buys == null)
         {
            //current linked list that will be added to.
            buys = new LinkedList<>();
            //So if that session never existed, it is added in. [Session 1: EMPTY, Session 2: Object1, Object2]
            buysFromSession.put(words[BUY_SESSION_ID], buys);
         }

         //if that session already existed, just add object in.
         buys.add(new Buy(words[BUY_SESSION_ID], words[BUY_PRODUCT_ID], Integer.parseInt(words[BUY_PRICE]), Integer.parseInt(words[BUY_QUANTITY]) ));

      }

   }




   private static void processEndEntry(final String[] words)
   {
      if (words.length != END_NUM_FIELDS)
      {
         return;
      }
   }




      //this is called by processFile below - its main purpose is
      //to process the data using the methods you write above
   private static void processLine(
      final String line,
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
      /* add parameters as needed */
   {
      final String[] words = line.split("\\h");

      if (words.length == 0)
      {
         return;
      }

      switch (words[0])
      {
         case START_TAG:
            processStartEntry(words, sessionsFromCustomer);
            break;
         case VIEW_TAG:
            processViewEntry(words, viewsFromSession);
            break;
         case BUY_TAG:
            processBuyEntry(words, buysFromSession);
            break;
         case END_TAG:
            processEndEntry(words /* add arguments as needed */ );
            break;
      }
   }




      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printSessionPriceDifference(final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
   {
      System.out.println("Price Difference for Purchased Product by Session");

      List<String> listofsessionswithpurchases = new LinkedList<>();

      //Gets list of sessions from all customers.
      for ( List<String> i: sessionsFromCustomer.values())
      {

         //Gets session IDs from all those sessions.
         for (String sessionIDS : i)
         {

            //sees if purchases is a Buy.
            List<Buy> purchases = buysFromSession.get(sessionIDS);

            //if not a buy
            if ( purchases != null)
            {  
               //add the sessionID
               listofsessionswithpurchases.add(sessionIDS);
            }
         }
      }

      //all sessions in side all sessions
      for (String session : listofsessionswithpurchases)
      {
         System.out.println(session);

         //went through all purchases in the sessions.
         //buysfromSession.get gives you list of buy objects
         for (Buy purchase : buysFromSession.get(session))
         {

            //grabbing product ID part
            String productIDs = purchase.getProduct();

            //grabbing price part
            int price = purchase.getprice();

            //counter for total price.
            int totalprice = 0;

            //total price of items viewed in in all sessions.
            for (View view : viewsFromSession.get(session))
            {
               totalprice += view.getprice();
            }

            double average = ((double)totalprice) / viewsFromSession.get(session).size();

            System.out.println("\t" + productIDs + " " + (price - average));

         }
      }

   }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printCustomerItemViewsForPurchase(final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
      /* add parameters as needed */
   {

      System.out.println("Number of Views for Purchased Product by Customer");

      //what items the customer purchased, how many times that customer viewed the item in all sessions that belong to them.

      //Gets list of sessions from all customers.
      for (String i: sessionsFromCustomer.keySet())
      {

         List<String> listofproducts = new LinkedList<>();

         //Gets individual customeer and all sessions associated with them.
         List<String> listofsessions = sessionsFromCustomer.get(i);

         //Gets session IDs from all those sessions.
         for (String session: listofsessions)
         {
            //purchases for every session
            List<Buy> listofbuys = buysFromSession.get(session);

            //if purchases exist for that session
            if (listofbuys != null)
            {
            	//Print out Customer - in here since I only want customers where there were purchases.
         		System.out.println(i);


               //into object in that session.
               for (Buy product : listofbuys)
               {

                  //if not containing product already.
                  if (!listofproducts.contains(product))
                  {

                     //add that product to your list.
                     listofproducts.add(product.getProduct());
                  }
               }
            }
         }


         //also in main for loop for customers.

         //going through each productID.
         for (String product : listofproducts)
         {
            int countviews = 0;

            //sessions per customer counted up.
            for (String session : sessionsFromCustomer.get(i))
            {

               //grabbing list of views from that session.
               List<View> viewList = viewsFromSession.get(session);

               //if not empty. So has views.
               if (viewList != null) 
               {

                  //go into view object.
                  for (View view: viewsFromSession.get(session))
                  {

                     //check if current productID matches our product in our list we are trying to find views for.
                     if ( view.getProduct().equals(product))
                     {
                        countviews += 1;
                        break;
                     }
                  }                  
               }
            }


            System.out.println("\t" + product + " " + countviews);

         }
   }

   System.out.println('\n');
}

      //write this after you have figured out how to store your data
      //make sure that you understand the problem


//======================================================================//

   private static void ViewAverage(final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
   {
      List<String> listofsessionswithoutpurchases = new LinkedList<>();

      //Gets list of sessions from all customers.
      for ( List<String> i: sessionsFromCustomer.values())
      {

         //Gets session IDs from all those sessions.
         for (String sessionIDS : i)
         {

            //sees if purchases is a Buy.
            List<Buy> purchases = buysFromSession.get(sessionIDS);

            //if not a buy
            if ( purchases == null)
            {  
               //add the sessionID
               listofsessionswithoutpurchases.add(sessionIDS);
            }
         }
      }

      //to count number of sessions
      int count = 0;

      //session inside list of sessions
      for (String session : listofsessionswithoutpurchases)
      {  
         //number of views inside single session
         List<View> viewsForSession = viewsFromSession.get(session);

         //added to counter
         if (viewsForSession != null) {
           count += viewsForSession.size();       	
         }

         

      }

      //average calculated with total views without purchases / the sessions.
      double result = ((double) count) / listofsessionswithoutpurchases.size();

      System.out.println("Average Views without Purchase: " + result);

   }

//======================================================================//

   private static void printStatistics(final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession) 
      /* add parameters as needed */

   {
      ViewAverage(sessionsFromCustomer, viewsFromSession, buysFromSession);
      printSessionPriceDifference(sessionsFromCustomer, viewsFromSession, buysFromSession);
      printCustomerItemViewsForPurchase(sessionsFromCustomer, viewsFromSession, buysFromSession);

      /* This is commented out as it will not work until you read
         in your data to appropriate data structures, but is included
         to help guide your work - it is an example of printing the
         data once propogated 
         printOutExample(sessionsFromCustomer, viewsFromSession, buysFromSession);
      */
   }

   /* provided as an example of a method that might traverse your
      collections of data once they are written 
      commented out as the classes do not exist yet - write them! */

   private static void printOutExample(
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession) 
   {
      //for each customer, get their sessions
      //for each session compute views
      for(Map.Entry<String, List<String>> entry: 
         sessionsFromCustomer.entrySet()) 
      {
         System.out.println(entry.getKey());
         List<String> sessions = entry.getValue();
         for(String sessionID : sessions)
         {
            System.out.println("\tin " + sessionID);
            List<View> theViews = viewsFromSession.get(sessionID);

            if (theViews != null) {
             for (View thisView: theViews)
            	{

               System.out.println("\t\tviewed " + thisView.getProduct());
            	}           	
            }

         }
      }
   }


      //called in populateDataStructures
   private static void processFile(
      final Scanner input,
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
      /* add parameters as needed */
   {
      while (input.hasNextLine())
      {
         processLine(input.nextLine(), sessionsFromCustomer, viewsFromSession, buysFromSession);
            /* add arguments as needed */
      }
   }

      //called from main - mostly just pass through important data structures
   private static void populateDataStructures(
      final String filename,
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<View>> viewsFromSession,
      final Map<String, List<Buy>> buysFromSession)
      /* add parameters as needed */

      throws FileNotFoundException
   {
      try (Scanner input = new Scanner(new File(filename)))
      {
         processFile(input, sessionsFromCustomer, viewsFromSession, buysFromSession);
            /* add arguments as needed */
      }
   }

   private static String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }

   //Takes in file name
   public static void main(String[] args)
   {
      /* Map from a customer id to a list of session ids associated with
       * that customer.
       */

      final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
      final Map<String, List<View>> viewsFromSession = new HashMap<>();
      final Map<String, List<Buy>> buysFromSession = new HashMap<>();

      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */

      final String filename = getFilename(args);

      try
      {
         populateDataStructures(filename, 
            sessionsFromCustomer, 
            viewsFromSession, 
            buysFromSession);
            /* add parameters as needed */

         printStatistics(sessionsFromCustomer, viewsFromSession, buysFromSession);
            /* add parameters as needed */

      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }

      printOutExample(sessionsFromCustomer, viewsFromSession, buysFromSession);
   }
}
