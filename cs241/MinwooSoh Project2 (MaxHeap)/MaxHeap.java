import java.util.Arrays;
/**
   A class for creating and interacting with a heap tree of size 100.
   @author Minwoo Soh
*/
public class MaxHeap<T extends Comparable<? super T>>
{
  private T[] heap;
  private int lastIndex;
  private int optimalCount; // Number of swaps using optimal method of adding.

  /** Creates a Max Heap Tree with space for 100 entries. */
  public MaxHeap()
  {
    @SuppressWarnings("unchecked")
    T[] tempHeap = (T[]) new Comparable[101];
    heap = tempHeap;
    lastIndex = 0;
    optimalCount = 0;
  } // end default constructor

  /** Creates a Max Heap Tree with exiting 100 entires.
      Adds all the entries first, then reorganizes them afterwards.
      @param entires  An existing array with unorganized entries. */
  public MaxHeap(T[] entries)
  {
    this(); // Create heap with 100 spaces
    // Fill the heap with all entries unorganized.
    for(int index = 0; index < entries.length; index++)
    {
      heap[index + 1] = entries[index];
      lastIndex++;
    }
    // Once all entries are inserted, reheap to organize the tree.
    for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
    {
      optimalCount += reheap(rootIndex);
    }
  } // end constructor

  /** Gets the highest value (root) in the max heap.
      @return  Data entry of the max (root), or null if tree is empty. */
  public T getMax()
  {
    T root = null;
    if(!isEmpty())
    {
      root = heap[1];
    }
    return root;
  } // end getMax

  /** Check if the tree is empty.
      @return  True if tree is empty, or false if not. */
  public boolean isEmpty()
  {
    return lastIndex < 1;
  } // end isEmpty

  /** Returns the number of entries the tree.
      @return  The integer amount of the tree size. */
  public int getSize()
  {
    return lastIndex;
  } // end getSize

  /** Delete every entry in the tree. */
  public void clear()
  {
    while(lastIndex > -1)
    {
      heap[lastIndex] = null;
      lastIndex--;
    }
    lastIndex = 0;
  } // end clear

  /** Adds a new entry to the tree and upheaps.
      @param newEntry  New data entry.
      @return  The total number of swaps during the add procedure. */
  public int add(T newEntry)
  {
    int swaps = 0;
    int newIndex = lastIndex + 1;
    int parentIndex = newIndex / 2;
    // Check if newEntry is greater than its parent.
    // The index to place the newEntry is found when the while loop ends.
    while( (parentIndex > 0) && (newEntry.compareTo(heap[parentIndex]) > 0))
    {
      heap[newIndex] = heap[parentIndex]; // Move down the parent
      newIndex = parentIndex; // Move to new index
      parentIndex = newIndex / 2; // Get parent of new index
      swaps++;
    }
    heap[newIndex] = newEntry;
    lastIndex++;
    return swaps;
  } // end add

  /** Remove the highest value (root) and then reheap.
      @return  Data entry of the removed node, or null if tree is empty. */
  public T removeMax()
  {
    T root = null;
    if(!isEmpty())
    {
      root = heap[1];
      heap[1] = heap[lastIndex];
      lastIndex--;
      reheap(1);
    }
    return root;
  } // end removeMax

  /** Reorganizes the heap tree after the max and last index are switched.
      Private method for the removeMax method.
      @param rootIndex  The index to start down heap from.
      @return  The total number of swaps during the reheap procedure. */
  private int reheap(int rootIndex)
  {
    int swaps = 0; // Counter for swaps
    boolean done = false;
    T orphan = heap[rootIndex]; // Entry that will be reorganized.
    int leftChildIndex = 2 * rootIndex;
    while(!done && (leftChildIndex <= lastIndex) )
    {
      int largerChildIndex = leftChildIndex;
      int rightChildIndex = leftChildIndex + 1;
      // Check which child is greater. The larger child will be set as the largerChildIndex.
      if( (rightChildIndex <= lastIndex) && (heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) )
      {
        largerChildIndex = rightChildIndex;
      }
      // Determine if orphan continues down the tree.
      if(orphan.compareTo(heap[largerChildIndex]) < 0)
      {
        heap[rootIndex] = heap[largerChildIndex]; // Move up child
        rootIndex = largerChildIndex; // Move down rootIndex
        leftChildIndex = 2 * rootIndex;
        swaps++;
      }
      else // Orphan is greater, the index to place orphan is found.
      {
        done = true;
      }
    }
    heap[rootIndex] = orphan;
    return swaps;
  } // end reheap

  /** Shows the first ten entries in the tree.
      @return  String with the first ten entries in order. */
  public String firstTen()
  {
    String result = "";
    for(int index = 1; index <= 10; index++)
    {
      result += heap[index] + ",";
    }
    result += "...";
    return result;
  } // end firstTen

  /** Shows the number of swaps when tree was made with the optimal adding method.
      @return  The number of swaps in an integer. */
  public int getOptimalCount()
  {
    return optimalCount;
  } // end getOptimalCount
} // end of MaxHeap
