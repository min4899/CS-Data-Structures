/**
   A fixed size array that implements ListInterface.
   @author Minwoo Soh
*/
public final class FixedSizeArrayList<T> implements ListInterface<T>
{
  private final T[] list;
  private int numberOfEntries;

  /** Creates an empty array with fixed capacity of 10. */
  public FixedSizeArrayList()
  {
    @SuppressWarnings("unchecked")
    T[] tempList = (T[])new Object[10]; // Unchecked cast.
    list = tempList;
    numberOfEntries = 0;
  } // end constructor

  /** Add an entry to the end of the List.
      The list size will be increased by 1.
      Other item positions will be unaffected.
      @param item  The object to be added. */
  public void add(T item)
  {
    /*
    if(numberOfEntries < 10)
    {
      list[numberOfEntries] =  item;
      numberOfEntries++;
    }
    else
    {
      System.out.println("Maximum amount of entries already in list. " +
                         "Cannot insert new entry.");
    } // end if
    */

    add(numberOfEntries + 1, item);
  } // end add

  /** Add an entry to the specificed position of the list.
      Users can choose positions 1 - 10.
      The list size will be increased by 1.
      Other item positions at or below specified position will be effected.
      @param item  The object to be added.
      @param position  The location the item should be placed in the list. Can choose between 1 - 10.
      @throws  IndexOutOfBoundsException if either position < 1 or position > getLength() + 1. */
  public void add(int position, T item)
  {
    try
    {
      if(numberOfEntries < 10)
      {
        if ( (position >= 1) && (position <= numberOfEntries + 1) )
        {
          makeRoom(position);
          list[position - 1] = item;
          numberOfEntries++;
        }
        else
        {
          throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        } // end if
      }
      else
      {
        System.out.println("Maximum amount of entries already in list. " +
                           "Cannot insert new entry.");
      } // end if
    } // end try
    catch(IndexOutOfBoundsException e)
    {
      System.out.println(e.getMessage());
    } // end catch
  } // end add

  /** Remove an entry at the specified position of the list.
      Users can choose positions 1 - 10.
      The list size will be decreased by 1.
      Other item positions at or below specified position will be effected.
      @param position  The location of item to be removed.
      @return  A reference to the removed entry.
      @throws  IndexOutOfBoundsException if either position < 1 or position > getLength() + 1. */
  public T remove(int position)
  {
    T result = null;
    try
    {
      if(isEmpty())
      {
        System.out.print("List is currently empty. No entries to remove.");
      }
      else
      {
        if ( (position >= 1) && (position <= numberOfEntries + 1) )
        {
          result = list[position - 1];
          list[position - 1] = null;
          removeGap(position);
          numberOfEntries--;
        }
        else
        {
          throw new IndexOutOfBoundsException("Given position of remove is out of bounds.");
        } // end if
      } // end if
    } // end try
    catch(IndexOutOfBoundsException e)
    {
      System.out.println(e.getMessage());
    } // end catch

    return result;
  } // end remove

  /** Removes all entries from list. */
  public void clear()
  {
    // Or set numberOfEntries = 0;

    while(!isEmpty())
    {
      remove(numberOfEntries);
    } // end while
  } // end clear

  /** Replaces the entry at the specificed position with a new entry.
      Users can choose positions 1 - 10.
      @param position  The location of item to be replaced.
      @param item  The new entry to replace old entry.
      @return  The original entry that was replaced.
      @throws  IndexOutOfBoundsException if either position < 1 or position > getLength() + 1. */
  public T replace(int position, T item)
  {
    T result = null;
    try
    {
      if(isEmpty())
      {
        System.out.println("List is currently empty. No entries to remove. ");
      }
      else
      {
        if ( (position >= 1) && (position <= numberOfEntries + 1) )
        {
          result = list[position - 1];
          list[position - 1] = item;
        }
        else
        {
          throw new IndexOutOfBoundsException("Given position of replace is out of bounds.");
        } // end if
      } // end if
    } // end try
    catch(IndexOutOfBoundsException e)
    {
      System.out.println(e.getMessage());
    } // end catch

    return result;
  } // end replace

  /** Retrieves the entry at the specificed position of the list.
      Users can choose positions 1 - 10.
      @param position  The specified location of entry.
      @return  A reference to the indicated entry.
      @throws  IndexOutOfBoundsException if either position < 1 or position > getLength() + 1. */
  public T view(int position)
  {
    T result = null;
    try
    {
      if(isEmpty())
      {
        System.out.println("List is currently empty. No entries to remove. ");
      }
      else
      {
        if ( (position >= 1) && (position <= numberOfEntries + 1) )
        {
          result = list[position - 1];
        }
        else
        {
          throw new IndexOutOfBoundsException("Given position of view is out of bounds.");
        } // end if
      } // end if
    } // end try
    catch(IndexOutOfBoundsException e)
    {
      System.out.println(e.getMessage());
    } // end catch

    return result;
  } // end view

  /** See whether this list contains a given entry.
      @param item  The object that is the desired entry.
      @return  True if the list contains the item, false if not. */
  public boolean contains(T item)
  {
    boolean found = false;
    int index = 0;

    while(!found && (index < numberOfEntries))
    {
      if(item.equals(list[index]))
      {
        found = true;
      } // end if
      index++;
    } // end while

    return found;
  } // end contains

  /** Gets the length of the list.
      @return  The integer number of entries currently in the list. */
  public int getLength()
  {
    return numberOfEntries;
  } // end getLength

  /** Checks whether the list is empty.
      @return  True if the list is empty, false if not. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Retrieves all entries in the order in which they occur in the list.
      @return  A newly created array of all entries in the list. */
  public T[] toArray()
  {
    @SuppressWarnings("unchecked")
    T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast.
    for(int i = 0; i < numberOfEntries; i++)
    {
      result[i] = list[i];
    } // end for

    return result;
  } // end toArray

  /** Makes room for a new entry at position. Shifts entries after position.
      @param position  The position where new entry will be inserted. */
  private void makeRoom(int position)
  {
    int newIndex = position - 1;
    int lastIndex = numberOfEntries - 1;

    for(int i = lastIndex; i >= newIndex; i--)
    {
      list[i + 1] = list[i];
    } // end for
  } // end makeRoom

  /** Shifts entries that are beyond the entry to be removed to the next lower position.
      @param position  The position where entry was removed. */
  public void removeGap(int position)
  {
    int removedIndex = position - 1;
    int lastIndex = numberOfEntries - 1;

    for(int i = removedIndex; i < lastIndex; i++)
    {
      list[i] = list[i + 1];
    } // end for

    list[lastIndex] = null;
  } // end removeGap
} // end of FixedSizeArrayList
