import java.util.Iterator;
/**
   An interface for traversing trees.
   @author Minwoo Soh
*/
public interface TreeIteratorInterface<T>
{
  public Iterator<T> getPreorderIterator();
  public Iterator<T> getPostorderIterator();
  public Iterator<T> getInorderIterator();
  public Iterator<T> getLevelOrderIterator();
} // end of TreeIteratorInterface
