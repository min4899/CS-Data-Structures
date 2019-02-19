/**
A class of bags implemented using fixed sized

*/
public final class ArrayBag<T> implements BagInterface<T>
{
  //number variables
  private static final int DEFAULT_CAPACITY = 20; //default size of bag
  private final T[] bag; //actual bag that will be made
  private int numOfEntries;

  public ArrayBag()
  {
    this(DEFAULT_CAPACITY);
  }

  public ArrayBag(int desiredSize)
  {
    @SuppressWarnings("unchecked")
    T[] tempBag = (T[])new Object[desiredSize]; //this is an unchecked cast
    bag = tempBag;
    numOfEntries = 0;
  }

  /** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry)
  {
    boolean result = true;
    //check if it is possible to add
    if(isBagFull())
    {
      //Bag is full
      result = false;
    }
    else
    {
      bag[numOfEntries] = newEntry;
      numOfEntries++;
    }
    return result;
  }

  ///STUB code
  public int getCurrentSize()
  {
    int x = 1;
    reutrn x;
  }

  //define later on
  //True = FULL
  //False = Still has room
  private boolean isBagFull()
  {
    return true;
  }

  public T remove()
  {
    if(isEmpty())
    {
      //Notify Bag is Empty
    }
    else
    {
      T item = new[numOfEntries-1]; //setting item inside temp
      bag[numOfEntries-1] = null; //removing item from bag
      numOfEntries--; //decrements item in bag count
      return item;
    }
  }

  public boolean remove(T anEntry) //check if item is in Bag, if so remove it then realign Bag
  {
    int itemLocation = 0;
    boolean result = false;
    if(contains(anEntry)) //I know item is in the bag
    {
      for(int i = 0; i < numOfEntries; i++)
      {
        if(bag[i].equals(anEntry)
        {
          itemLocation = i; //set index of item
          result = true;
        }
      }
      //remove item and organize bag
      bag[itemLocation] = null;
      numOfEntries--;

      for(int i = itemLocation; i < numOfEntries; i++)
      {
        bag{i} = bag[i+1];
      }
    }
    else //item is NOT in bag
    {

    }
  }

}
