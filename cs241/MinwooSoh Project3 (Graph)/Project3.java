import java.util.Scanner;
/**
   A main program for testing out Digraph.java.
   @author Minwoo Soh
*/
public class Project3
{
  public static void main(String[] args)
  {
    // Create a digraph with the given values from city.dat and road.dat.
    Digraph digraph = new Digraph();
    // Create a scanner object to receive user input.
    Scanner keyboard = new Scanner(System.in);

    if(digraph.checkInitialization()) // If digraph was properly initialized.
    {
      // resume will be false only when user exits the program (when user enters "E").
      boolean resume = true;
      while(resume)
      {
        System.out.print("Command? ");
        String input = keyboard.nextLine();
        switch(input) // Valid inputs are: Q, D, I, R, H, E.
        {
          case "Q" : {
            System.out.print("City code: ");
            input = keyboard.nextLine(); // Save city code.
            System.out.println(digraph.getCityInfo(input)); // Return city information.
            break;
          }
          case "D" : {
            System.out.print("City codes: ");
            input = keyboard.nextLine();
            String command[] = input.split(" "); // Save the 2 city codes in array of size 2.
            if(command.length == 2) // Array must have 2 elements only (2 city codes).
            {
              digraph.dijkstra(command[0], command[1]);
            }
            else
            {
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "I" : {
            System.out.print("City codes and distance: ");
            input = keyboard.nextLine();
            String command[] = input.split(" "); // Save 2 city codes and 1 distance value in array of size 3.
            if(command.length == 3) // Array must have 3 elements only (2 city codes and 1 distance)
            {
              try
              {
                int distance = Integer.parseInt(command[2]); // Get distance in int form.
                digraph.addEdge(command[0], command[1], distance);
              }
              // If the input for distance was not a number value.
              catch(NumberFormatException e)
              {
                System.out.println("Please provide a valid input.");
              }
            }
            else
            {
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "R" : {
            System.out.print("City codes: ");
            input = keyboard.nextLine();
            String command[] = input.split(" "); // Save the 2 city codes in array of size 2.
            if(command.length == 2) // Array must have 2 elements only (2 city codes).
            {
              digraph.removeEdge(command[0], command[1]);
            }
            else
            {
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "H" : {
            System.out.println("   Q   Query the city information by entering the city code.");
            System.out.println("   D   Find the minimum distance between two cities.");
            System.out.println("   I   Insert a road by entering two city codes and distance.");
            System.out.println("   R   Remove an existing road by entering two city codes.");
            System.out.println("   H   Display this message.");
            System.out.println("   E   Exit. ");
            break;
          }
          case "E" : {
            resume = false; // while loop stops when resume = false.
            break;
          }
          default :
            System.out.println("Please provide a valid input.");
        } // end switch
      } // end while
    }
    else // Files not found, not initialized.
    {
      System.out.println("Files not found. Please put city.dat and road.dat files inside the directory.");
    }
  } // end main method
} // end of Program
