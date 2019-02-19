/**
   An interface for the ADT deque.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public interface DequeInterface<T>
{
   /** Adds a new entry to the front/back of this dequeue.
       @param newEntry  An object to be added. */
   public void addToFront(T newEntry);
   public void addToBack(T newEntry);

   /** Removes and returns the front/back entry of this dequeue.
       @return  The object at the front/back of the dequeue.
       @throws  EmptyQueueException if the dequeue is empty before the operation. */
   public T removeFront();
   public T removeBack();

   /** Retrieves the front/back entry of this dequeue.
       @return  The object at the front/back of the dequeue.
       @throws  EmptyQueueException if the dequeue is empty before the operation. */
   public T getFront();
   public T getBack();

   /**  Detects whether this dequeue is empty.
       @return  True if the queue is empty, or false otherwise. */
   public boolean isEmpty();

   /*  Removes all entries from this dequeue. */
   public void clear();
} // end DequeInterface
