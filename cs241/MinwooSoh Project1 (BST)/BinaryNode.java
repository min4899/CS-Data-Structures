/**
   A class for binary nodes.
   @author Minwoo Soh
*/
class BinaryNode<T>
{
  private T data;
  private BinaryNode<T> leftChild;
  private BinaryNode<T> rightChild;

  /** Creates an empty Binary Node with no data or children. */
  public BinaryNode()
  {
    this(null);
  } // end default constructor

  /** Creates a Binary Node with a data entry but no children.
      @param data  The data to be stored. */
  public BinaryNode(T data)
  {
    this(data, null, null);
  } // end constructor

  /** Creates a Binary Node with a data entry and children.
      @param data  The data to be stored.
      @param leftChild  Node that will be the left child.
      @param rightChild  Node that will be the right child. */
  public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild)
  {
    this.data = data;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  } // end constructor

  /** Get the data entry stored in the node.
      @return  The data of the node. */
  public T getData()
  {
    return data;
  } // end getData

  /** Set a new data entry to store in the node.
      @param data  New data to be stored. */
  public void setData(T data)
  {
    this.data = data;
  } // end setData

  /** Get the node's left child node.
      @return  The node set as the left child. */
  public BinaryNode<T> getLeftChild()
  {
    return leftChild;
  } // end getLeftChild

  /** Replace the left child node with a new node.
      @param leftChild  New node to replace left child. */
  public void setLeftChild(BinaryNode<T> leftChild)
  {
    this.leftChild = leftChild;
  } // end setLeftChild

  /** Check if the node has a left child.
      @return  True if node has a left child, or false if not. */
  public boolean hasLeftChild()
  {
    return leftChild != null;
  } // end hasLeftChild

  /** Get the node's right child node.
      @return  The node set as the right child. */
  public BinaryNode<T> getRightChild()
  {
    return rightChild;
  } // end getRightChild

  /** Replace the right child node with a new node.
      @param rightChild  New node to replace right child. */
  public void setRightChild(BinaryNode<T> rightChild)
  {
    this.rightChild = rightChild;
  } // end setRightChild

  /** Check if the node has a right child.
      @return  True if node has a right child, or false if not. */
  public boolean hasRightChild()
  {
    return rightChild != null;
  } // end hasRightChild

  /** Check if the node is a leaf.
      @return  True if node is a leaf, or false if not. */
  public boolean isLeaf()
  {
    return (leftChild == null) && (rightChild == null);
  } // end isLeaf

  /** Get the number of nodes that the selected tree of nodes contains.
      @return  An integer count of the all nodes in the current tree. */
  public int getNumberOfNodes()
  {
    int leftCounter = 0;
    int rightCounter = 0;
    if(leftChild != null)
    {
      leftCounter = leftChild.getNumberOfNodes();
    }
    if(rightChild != null)
    {
      rightCounter = rightChild.getNumberOfNodes();
    }
    return 1 + leftCounter + rightCounter;
  } // end getNumberOfNodes

  /** Get the heigh of the current node.
      @return  An integer value of the node's height. */
  public int getHeight()
  {
    return getHeight(this);
  } // end getHeight

  /** A private method that counts the max height of the node for the getHeight method.
      @param node  The requested node that needs to be counted.
      @return  An integer value of the node's height */
  private int getHeight(BinaryNode<T> node)
  {
    int height = 0;
    if(node != null)
    {
      height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }
    return height;
  } // end private method of getHeight

  /** Creates a binary tree copy of the current node.
      @return  Returns the copy of the selected binary tree. */
  public BinaryNode<T> copy()
  {
    BinaryNode<T> newRoot = new BinaryNode<T>(data);
    if(leftChild != null)
    {
      newRoot.setLeftChild(leftChild.copy()); // Create the left subtree.
    }
    if(rightChild != null)
    {
      newRoot.setRightChild(rightChild.copy()); // Create the right subtree.
    }
    return newRoot;
  } // end of copy

  /** Get the data entry of the node that is at the left edge of the tree.
      @return  The data entry of the node. */
  public T getLeftMostData()
  {
    if(leftChild == null)
    {
      return data;
    }
    else
    {
      return leftChild.getLeftMostData();
    }
  } // end getLeftMostData

  /** Get the data entry of the node that is at the right edge of the tree.
      @return  The data entry of the node. */
  public T getRightMostData()
  {
    if(rightChild == null)
    {
      return data;
    }
    else
    {
      return rightChild.getRightMostData();
    }
  } // end getRightMostData

  /** Remove the node at the left edge of the tree.
      @return  The child node or null to be set as the new left child. */
  public BinaryNode<T> removeLeftMost()
  {
    if(leftChild == null) // If left most node is found.
    {
      return rightChild; // Return right child if it exists, otherwise returns null
      // The parent of this node will have a new child:
      // null or right child of the deleted node.
    }
    else
    {
      leftChild = leftChild.removeLeftMost(); // Keep traversing and set new left child.
      // Left child will be set as the return binary node.
      return this;
    }
  } // end removeLeftMost

  /** Remove the node at the right edge of the tree.
      @return  The child node or null to be set as the new right child. */
  public BinaryNode<T> removeRightMost()
  {
    if(rightChild == null) // If right most node is found.
    {
      return leftChild; // Return left child if it exists, otherwise returns null
      // The parent of this node will have a new child:
      // null or left child of the deleted node.
    }
    else
    {
      rightChild = rightChild.removeRightMost(); // Keep traversing and set new right child.
      // Right child will be set as the return binary node.
      return this;
    }
  } // end removeRightMost
} // end of BinaryNode
