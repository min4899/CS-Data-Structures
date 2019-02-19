import java.util.Scanner;
/**
   A class for testing out BinarySearchTree.java.
   @author Minwoo Soh
*/
public class Project1
{
  public static void main(String[] args)
  {
    // Create an empty Binary Search Tree.
    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
    // Create a scanner object to receive user input.
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Please enter the initial sequence of values:");

    String line = keyboard.nextLine();
    // Get each integer in the input sequence and put them in an array.
    String numbers[] = line.split(" ");
    // Add each integer one by one into the binary serach tree.
    for(int i = 0; i < numbers.length; i++)
    {
      tree.add(Integer.parseInt(numbers[i]));
    }

    System.out.println("Pre-order: " + tree.getPreorder());
    System.out.println("In-order: " + tree.getInorder());
    System.out.println("Post-order: " + tree.getPostorder());

    // resume will be false only when user exits the program (when user enters "E")
    boolean resume = true;
    while(resume)
    {
      System.out.print("Command? ");
      String input = keyboard.nextLine();
      String command[] = input.split(" "); // Save the input command into an array.
      if((command.length <= 2) && (command.length >= 1)) // Check if input is a valid input.
      {
        // The valid commands are:
        //   "I (integer: new data entry)" - Insert a new entry into the tree.
        //   "D (integer: data entry to remove)" - Delete the specified entry from the tree.
        //   "P (integer: data entry)" - Get the Predecessor of the specified entry.
        //   "S (integer: data entry)" - Get the Successor of the specified entry.
        //   "E" - Exist Program.
        //   "H" - Get the list of valid commands.
        switch(command[0])
        {
          case "I" : {
            try
            {
              int number = Integer.parseInt(command[1]); // Get the integer of the command.
              Object result = tree.add(number); // Run method from BinarySearchTree object.
              if(result == null) // Entry is successfully added.
              {
                System.out.println("In-order: " + tree.getInorder());
              }
              else // Entry already exists.
              {
                System.out.println(number + " already exists, ignore.");
              }
            }
            catch(NumberFormatException e)
            {
              // The value entered after "I" must be a single integer.
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "D" : {
            try
            {
              int number = Integer.parseInt(command[1]); // Get the integer of the command.
              Object result = tree.remove(number); // Run method from BinarySearchTree object.
              if(result == null) // Specified entry does not exist in the tree.
              {
                System.out.println(number + " doesn't exist!");
              }
              else // Entry is successully deleted.
              {
                System.out.println("In-order: " + tree.getInorder());
              }
            }
            catch(NumberFormatException e)
            {
              // The value entered after "D" must be a single integer.
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "P" : {
            try
            {
              int number = Integer.parseInt(command[1]); // Get the integer of the command.
              Object result = tree.getPredecessor(number); // Run method from BinarySearchTree object.
              // Specified entry does not exist in the tree or predecessor does not exist for this entry.
              if(result == null)
              {
                System.out.println("Predecessor for " + number + " doesn't exist!");
              }
              else // Predecessor for entry is found.
              {
                System.out.println(result);
              }
            }
            catch(NumberFormatException e)
            {
              // The value entered after "P" must be a single integer.
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "S" : {
            try
            {
              int number = Integer.parseInt(command[1]); // Get the integer of the command.
              Object result = tree.getSuccessor(number); // Run method from BinarySearchTree object.
              // Specified entry does not exist in the tree or successor does not exist for this entry.
              if(result == null)
              {
                System.out.println("Successor for " + number + " doesn't exist!");
              }
              else // Successor for entry is found.
              {
                System.out.println(result);
              }
            }
            catch(NumberFormatException e)
            {
              // The value entered after "S" must be a single integer.
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "E" : {
            if(command.length == 1) // Input must be only "E" and nothing else.
            {
              System.out.println("Thank you for using my program!");
              resume = false; // Stop program.
            }
            else
            {
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          case "H" : {
            if(command.length == 1) // Input must be only "H" and nothing else.
            {
              System.out.println(" I  Insert a value");
              System.out.println(" D  Delete a value");
              System.out.println(" P  Find predecessor");
              System.out.println(" S  Find successor");
              System.out.println(" E  Exit the program");
              System.out.println(" H  Display this message");
            }
            else
            {
              System.out.println("Please provide a valid input.");
            }
            break;
          }
          default :
            System.out.println("Please provide a valid input.");
        } // end switch
      } // end if
      else
      {
        System.out.println("Please provide a valid input.");
      }
    } // end while
  } // end main method
} // end of Program
