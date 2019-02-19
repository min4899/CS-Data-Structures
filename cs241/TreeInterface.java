/**
   An interface for a trees.
   @author Minwoo Soh
*/
public interface TreeInterface<T>
{
  public T getRootData();
  public int getHeight();
  public int getNumberOfNodes();
  public boolean isEmpty();
  public void clear();
} // end of TreeInterface
