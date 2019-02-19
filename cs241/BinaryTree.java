import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*;
/**
   A class for creating and interacting with a binary tree.
   @author Minwoo Soh
*/
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
  private BinaryNode<T> root;

  // constructors
  public BinaryTree()
  {
    root = null;
  }

  public BinaryTree(T rootData)
  {
    root = new BinaryNode<>(rootData);
  }

  public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
    privateSetTree(rootData, leftTree, rightTree);
  }

  // set methods
  public void setTree(T rootData)
  {
    root = new BinaryNode<T>(rootData);
  }

  public void setTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
    privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
  }

  private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
    
  }
}
