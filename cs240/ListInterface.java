/**
    An Interface of an ADT LIST.
    @author Minwoo Soh
    @version 1.0
*/
 public interface ListInterface<T>
 {
   /** Add an entry to the end of the List.
       The list size will be increased by 1.
       Other item positions will be unaffected.
      @param item  The object to be added. */
   public void add(T item);

   /** Add an entry to the specificed position of the list.
       The list size will be increased by 1.
       Other item positions at or below specified position will be effected.
      @param item  The object to be added.
      @param position  The location the item should be placed in the list.
      @throws IndexOutOfBoundException  If either position < 1 or position > getLength() + 1 */
   public void add(int position, T item);

   /** Remove an entry at the specified position of the list.
       The list size will be decreased by 1.
       Other item positions at or below specified position will be effected.
      @param position  The location of item to be removed.
      @throws IndexOutOfBoundException  If either position < 1 or position > getLength() + 1 */
   public void remove(int position);

   /** Removes all entries from list. */
   public void clear();

   /** Retrieves the entry at the specificed position of the list.
      @param position  The specified location of entry.
      @throws IndexOutOfBoundException  If either position < 1 or position > getLength() + 1 */
   public T view(int position);
 } // end ListInterface
