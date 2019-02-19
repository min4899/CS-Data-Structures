import java.util.Iterator;
/**
   An interface for binary search trees specifically for project 1.
   @author Minwoo Soh
*/
public interface SearchTreeInterface<T extends Comparable<? super T>>
{
  public boolean contains(T entry);
  public T getEntry(T entry);
  public T add(T newEntry);
  public T remove(T entry);

  public T getRootData();
  public boolean isEmpty();
  public void clear();

  //public Iterator<T> getPreorderIterator();
  //public Iterator<T> getPostorderIterator();
  //public Iterator<T> getInorderIterator();
} // end of SearchTreeInterface
