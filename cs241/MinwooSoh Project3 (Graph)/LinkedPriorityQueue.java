/**
   A single linked priority queue that implements PriorityQueueInterface.
   @author Minwoo Soh
*/
public final class LinkedPriorityQueue<T extends Comparable<T>>
{
  private Node firstNode;
  private Node lastNode;
  private int numberOfEntries;

  /** Creates an empty LinkedPriorityQueue object with no nodes. */
  public LinkedPriorityQueue()
  {
    firstNode = null;
    lastNode = null;
    numberOfEntries = 0;
  } // end constructor

  /** Adds a new entry to this priority queue.
      @param newEntry  An object to be added. */
  public void add(T newEntry)
  {
    Node newNode = new Node(newEntry);

    if(isEmpty())
    {
      firstNode = newNode;
      lastNode = newNode;
      numberOfEntries++;
    }
    else
    {
      // The lower value has the higher priority.
      if(newNode.compareTo(firstNode) < 0) // If entry is lower than firstNode. First in Queue.
      {
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
      }
      else if(newNode.compareTo(lastNode) >= 0) // If entry is lower than or equal to lastNode. Last in Queue.
      {
        lastNode.next = newNode;
        lastNode = newNode;
        numberOfEntries++;
      }
      else // Check entries after firstNode and before lastNode.
      {
        Node currentNode = firstNode;
        boolean inserted = false;
        while( (currentNode.next != null) && (inserted == false))
        {
          if(newNode.compareTo(currentNode.next) < 0)
          {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            inserted = true;
          } // end if
          currentNode = currentNode.next;
        } // end while
      } // end if
      numberOfEntries++;
    } // end if
  } // end add

  /** Removes and returns the entry having the highest priority.
      @return  Either the object having the highest priority or,
               if the priority queue is empty before the operation, null. */
  public T remove()
  {
    Node<T> result = new Node(null);
    if(isEmpty())
    {
      return result.data;
    }
    else if(numberOfEntries == 1)
    {
      result = firstNode;
      firstNode = null;
      lastNode = null;
      numberOfEntries--;
    }
    else
    {
      result = firstNode;
      firstNode = firstNode.next;
      numberOfEntries--;
    }
    return result.data;
  } // end remove

  /** Retrieves the entry having the highest priority.
      @return  Either the object having the highest priority or,
               if the priority queue is empty, null. */
  public T peek()
  {
    Node<T> result = new Node(null);
    if(isEmpty())
    {
      return result.data;
    }
    else
    {
      result = firstNode;
      return result.data;
    } // end if
  } // end peek

  /** Detects whether this priority queue is empty.
      @return  True if the priority queue is empty, or false otherwise. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Gets the size of this priority queue.
      @return  The number of entries currently in the priority queue. */
  public int getSize()
  {
    return numberOfEntries;
  } // end getSize

  /** Removes all entries from this priority queue. */
  public void clear()
  {
    firstNode = null;
    lastNode = null;
    numberOfEntries = 0;
  } // end clear

  /** Returns all entries in an organized string.
      @return  String of all entries. */
  public String toString()
  {
    String result = "[";

    Node currentNode = firstNode;
    while(currentNode != null)
    {
      result += currentNode.data + " ";
      currentNode = currentNode.next;
    } // end for
    result += "]";
    return result;
  }

  /** Private inner class Node */
  private class Node<T extends Comparable<T>> implements Comparable<Node<T>>
  {
    private T data;
    private Node next; // Link to next node.
    /** Creates a node that points to null.
        @param dataPortion  The data that will be stored in the node. */
    private Node(T dataPortion)
    {
      this(dataPortion, null);
    } // end constructor

    /** Creates a node that points to another node.
        @param dataPortion  The data that will be stored in the node.
        @param nextNode  The previous node that will be referenced. */
    private Node(T dataPortion, Node nextNode)
    {
      data = dataPortion;
      next = nextNode;
    } // end constructor

    /** Compares two datas to see which has priority.
        @param other  The node that will be compared with another.
        @return  0 if equal, less than 0 if other is greater, greater than 0 if other is lower.*/
    public int compareTo(Node<T> other)
    {
      return data.compareTo(other.data);
    } // end compareTo
  } // end of Node
} // end of LinkedPriorityQueue
