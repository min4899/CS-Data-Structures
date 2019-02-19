import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
   A class for testing out MaxHeap.java.
   @author Minwoo Soh
*/
public class Project2
{
  public static void main(String[] args)
  {
    // Create a scanner object to receive user input.
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Please select how to test the program:");
    System.out.println("(1) 20 sets of 100 randomly generated integers");
    System.out.println("(2) Fixed integer value 1-100");
    System.out.print("Enter choice: ");
    try
    {
      int choice = keyboard.nextInt(); // Get user input
      switch(choice)
      {
        case 1 :
        {
          System.out.println();
          int count1 = 0; // Counter for sequential insertion swaps.
          int count2 = 0; // Counter for optimal method swaps.
          for (int set = 0; set < 20; set++)
          {
            // Create an empty Max Heap Tree.
            MaxHeap<Integer> tree = new MaxHeap<Integer>();

            // Create an array with 100 integers and no duplicates.
            ArrayList<Integer> list = new ArrayList<Integer>();
            while(list.size() < 100)
            {
              int number = (int)(Math.random() * 1000) + 1;
              if(!list.contains(number))
              {
                list.add(number);
              }
            }
            Integer[] input = new Integer[list.size()];
            input = list.toArray(input);

            // Sequential insertions
            for(int i = 0; i < input.length; i++)
            {
              count1 += tree.add(input[i]);
            }
            // Optimal method
            tree = new MaxHeap<Integer>(input);
            count2 += tree.getOptimalCount();
          } // Finish 20 sets
          count1 = count1 / 20; // Get averages
          count2 = count2 / 20;
          System.out.println("Average swaps for series of insertions: " + count1);
          System.out.println("Average swaps for optimal method: " + count2);
          break;
        } // end case 1
        case 2 :
        {
          System.out.println();
          // Create an empty Max Heap Tree.
          MaxHeap<Integer> tree = new MaxHeap<Integer>();
          // For sequential insertion.
          int count = 0; // Counter for sequntial insertion swaps.
          for(int i = 1; i <= 100; i++) // Add 1- 100 in tree.
          {
            count += tree.add(i);
          }
          System.out.println("Heap built using series of insertions: " + tree.firstTen());
          System.out.println("Number of swaps: " + count);
          for(int i = 0; i < 10; i++) // Remove max value ten times.
          {
            tree.removeMax();
          }
          System.out.println("Heap after 10 removals: " + tree.firstTen());

          // For optimal method.
          System.out.println();
          Integer[] list = new Integer[100]; // Create an array with 1- 100.
          for(int i = 0; i < list.length; i++)
          {
            list[i] = i + 1;
          }
          tree = new MaxHeap<>(list);
          System.out.println("Heap built using optimal method: " + tree.firstTen());
          System.out.println("Number of swaps: " + tree.getOptimalCount());
          for(int i = 0; i < 10; i++) // Remove max value ten times.
          {
            tree.removeMax();
          }
          System.out.println("Heap after 10 removals: " + tree.firstTen());
          break;
        } // end case 2
        default:
          System.out.println("Not a valid choice. Closing program.");
      } // end switch
    } // end try
    catch(InputMismatchException e)
    {
      System.out.println("Not a valid choice. Closing program.");
    } // end catch

  } // end main method
} // end of Program
