
/**
  A class implementing an ADT QUEUE using a double linked node
  @author Minwoo Soh
  @version 1.0
*/
public class DLDeque<T> implements DequeInterface<T>
{
  private DLNode head;  //pointer to beginning of line
  private DLNode tail;  //pointer to end of line

  public DLDeque()
  {
    head = null;
    tail = null;
  }

  public DLDeque(first person/thing in line)
  {
    //FILL IN LATER

  }

  public void addToFront(T newEntry)
  {
    //Create a new node
    //set it to the front of the line
    //fix all pointers
  }

  public void addToBack(T newEntry)
  {
    //Create a new nod
    //set it to the back of the line
    //fix all pointers
  }

  public T removeFront()
  {
    //check if data is there if NOT throw exception
    //hold data in temp variable
    //delete data in DLNode(for security)
    //set head to next node
    //ONLY if there is a DLNode that head is pointing to, move its prev to null
  }

  public T removeBack()
  {
    //check if data is there if NOT throw exception
    //hold data in temp variable
    //delete data in DLNode(for security)
    //set tail to prev node
    //ONLY if there a DLNode that tail is pointing to, move next to null
  }

  public T getFront()
  {
    //check if data is there if NOT throw exception
    //return front node
  }

  public T getBack()
  {
    //check if data is there if NOT throw exception
    //return back node
  }

  public boolean isEmpty()
  {
    //check if head and tail is pointing to null
  }

  public void clear()
  {

  }
}
