/**
   A class for creating and interacting with a binary search tree.
   @author Minwoo Soh
*/
public class BinarySearchTree<T extends Comparable<? super T>>
{
  private BinaryNode<T> root;

  /** Creates an empty Binary Search Tree with no root. */
  public BinarySearchTree()
  {
    root = null;
  } // end default constructor

  /** Creates a Binary Search Tree with a root node. */
  public BinarySearchTree(T rootEntry)
  {
    root = new BinaryNode<>(rootEntry);
  } // end constructor

  /** Check if the tree is empty.
      @return  True if tree is empty, or False if not. */
  public boolean isEmpty()
  {
    return root == null; // If there is no root node, tree is empty.
  } // end isEmpty

  /** Delete every node in the tree. */
  public void clear()
  {
    root = null; // Delete node saved in root to delete entire tree.
  } // end clear

  /** Get the data entry stored in the root node.
      @return  The data entry of the root node. */
  public T getRootData()
  {
    if(isEmpty())
    {
      return null;
    }
    else
    {
      return root.getData();
    }
  } // end getRootData

  /** Set a new data entry for the a root.
      @param rootData  The data entry to replace current data in root. */
  public void setRootData(T rootData)
  {
    root.setData(rootData);
  } // end setRootData

  /** Set a new node as the root.
      @param root  The new root to replace the root. */
  public void setRootNode(BinaryNode<T> root)
  {
    this.root = root;
  } // end setRootNode

  /** Return the node that is currently set as the root.
      @return  The root node. */
  public BinaryNode<T> getRootNode()
  {
    return root;
  } // end getRootNode

  /** Check if any node within the binary search tree has the specified data entry.
      @param entry  The data entry to search for.
      @return  True if data entry is found, or False if not. */
  public boolean contains(T entry)
  {
    return getEntry(entry) != null;
  } // end contains

  /** Get the data of specified entry within the tree.
      @param entry  The data entry to search for.
      @return  The data entry if it is found, or null if not. */
  public T getEntry(T entry)
  {
    return findEntry(getRootNode(), entry);
  } // end getEntry

  /** Private method for getEntry.
      @param rootNode  The node to check if it has the specific data entry.
      @param entry  The data entry to search for.
      @return  The data entry if it is found, or null if not. */
  private T findEntry(BinaryNode<T> rootNode, T entry)
  {
    T result = null;
    if(rootNode != null)
    {
      T rootEntry = rootNode.getData();
      if(entry.equals(rootEntry)) // Check if the specified entry matches the selected node.
      {
        result = rootEntry;
      }
      // Entry does not match.
      else if(entry.compareTo(rootEntry) < 0) // If Entry is less than node.
      {
        result = findEntry(rootNode.getLeftChild(), entry);
      }
      else // if entry is greater than node.
      {
        result = findEntry(rootNode.getRightChild(), entry);
      }
    }
    return result;
  } // end findEntry

  /** Find the node with the specified entry.
      @param rootNode The node to check if it has the specific data entry.
      @param entry  The data entry to search for.
      @return  The node with data entry if found, or null if it is not found.*/
  private BinaryNode<T> findNode(BinaryNode<T> rootNode, T entry)
  {
    BinaryNode<T> result = null;
    if(rootNode != null)
    {
      T rootEntry = rootNode.getData();
      if(entry.equals(rootEntry)) // Check if the specified entry matches the selected node.
      {
        result = rootNode;
      }
      // Entry does not match.
      else if(entry.compareTo(rootEntry) < 0) // If Entry is less than node.
      {
        result = findNode(rootNode.getLeftChild(), entry);
      }
      else // If entry is greater than node.
      {
        result = findNode(rootNode.getRightChild(), entry);
      }
    }
    return result;
  } // end findNode.

  /** Find the parent node of the specified node.
      @param rootNode The node to check if it has the specified node as a child (left child or right child).
      @param child  The child node of the wanted parent.
      @return  The parent node of the specified child node if found, or null if it is not found.*/
  private BinaryNode<T> findParent(BinaryNode<T> rootNode, BinaryNode<T> child)
  {
    BinaryNode<T> parent = null;
    if(child != null || rootNode != null)
    {
      // Check if child is in the left subtree or right subtree.
      if((rootNode.hasLeftChild() && rootNode.getLeftChild() == child) || (rootNode.hasRightChild() && rootNode.getRightChild() == child))
      {
        parent = rootNode;
      }
      else // Parent is not found.
      {
        // Determine which subtree the parent is located in.
        int position = child.getData().compareTo(rootNode.getData());
        if(position <= 0) // Parent is in left subtree.
        {
          parent = findParent(rootNode.getLeftChild(), child);
        }
        else // Parent is in right subtree.
        {
          parent = findParent(rootNode.getRightChild(), child);
        } //end third if
      } // end second if
    } // end first if
    return parent;
  } // end findParent

  /** Add a node with a new entry into the tree.
      @param newEntry  The new entry to insert within the tree.
      @return  The data entry is returned if it already exists inside the tree,
               or null if new entry is successfully added. */
  public T add(T newEntry)
  {
    T result = null;
    if(isEmpty())
    {
      setRootNode(new BinaryNode<T>(newEntry));
    }
    else
    {
      result = addEntry(getRootNode(), newEntry);
    }
    return result;
  } // end add

  /** A private method for the add method.
      @param rootNode  The node to compare positions with the new entry.
      @param newEntry  The new entry to insert within the tree.
      @return  The data entry is returned if it already exists inside the tree,
               or null if new entry is successfully added. */
  private T addEntry(BinaryNode<T> rootNode, T newEntry)
  {
    T result = null;
    int position = newEntry.compareTo(rootNode.getData());
    if(position == 0) // If the entry is already in the tree.
    {
      result = rootNode.getData();
      rootNode.setData(newEntry);
    }
    else if(position < 0) // If entry should be in the left subtree of the rootNode.
    {
      if(rootNode.hasLeftChild())
      {
        result = addEntry(rootNode.getLeftChild(), newEntry); // Keep traversing if child already exists.
      }
      else
      {
        rootNode.setLeftChild(new BinaryNode<T>(newEntry));
      }
    }
    else // If entry should be in the right subtree of rootNode.
    {
      if(rootNode.hasRightChild())
      {
        result = addEntry(rootNode.getRightChild(), newEntry); // Keep traversing if child already exists.
      }
      else
      {
        rootNode.setRightChild(new BinaryNode<T>(newEntry)); // Space found. Set new entry here.
      }
    }
    return result;
  } // end addEntry

  /** Delete the node with the specified entry.
      @param entry  The data entry to search for.
      @return  The data entry of the removed node, or null if entry was not found. */
  public T remove(T entry)
  {
    T oldEntry = null;
    BinaryNode<T> nodeToRemove = findNode(root, entry);
    if(nodeToRemove != null)
    {
      oldEntry = nodeToRemove.getData();
      removeFromRoot(nodeToRemove);
    }
    return oldEntry;
  } // end remove

  /** Remove the specified node. Private method for remove method.
      @param rootNode  The selected node to be removed. */
  private void removeFromRoot(BinaryNode<T> rootNode)
  {
    if((rootNode == root) && (rootNode.isLeaf())) // If there is only 1 node in the tree.
    {
      root = null;
    }
    else if(rootNode.isLeaf()) // If node is a leaf.
    {
      BinaryNode<T> parent = findParent(root, rootNode);
      if(parent.getLeftChild() == rootNode) // If the node is left child.
      {
        parent.setLeftChild(null); // Remove parent's reference pointer.
      }
      else // If the rootNode is right child.
      {
        parent.setRightChild(null); // Remove parent's reference pointer.
      }
    }
    else if(rootNode.hasLeftChild() && rootNode.hasRightChild()) // If node has 2 children.
    {
      // Retrieve the largest value node of the node's left subtree.
      // Then replace node's value with that value.
      T largestValue = rootNode.getLeftChild().getRightMostData();
      rootNode.setData(largestValue);

      if(rootNode.getLeftChild().isLeaf()) // If the node's left child is a leaf.
      {
        rootNode.setLeftChild(null);
      }
      else // The left subtree of the node has more than 1 nodes.
      {
        // Remove the node that originally had the largest value.
        rootNode.setLeftChild(rootNode.getLeftChild().removeRightMost());
      }
    }
    else if(rootNode.hasRightChild()) // If node only has a right child.
    {
      if(rootNode == root) // If the node to be removed is the root.
      {
        root = root.getRightChild();
      }
      else // If node is not the root.
      {
        BinaryNode<T> parent = findParent(root, rootNode);
        if(parent.getLeftChild() == rootNode) // If node is the left child.
        {
          // Replace the parent's left child as the node's left child.
          parent.setLeftChild(rootNode.getRightChild());
        }
        else // If node is the right child.
        {
          // Replace the parent's right child as the node's right child.
          parent.setRightChild(rootNode.getRightChild());
        }
      }
    }
    else // If node only has a left child.
    {
      if(rootNode == root) // If the node to be removed is the root.
      {
        root = root.getLeftChild();
      }
      else // If the node is not the root.
      {
        BinaryNode<T> parent = findParent(root, rootNode);
        if(parent.getLeftChild() == rootNode) // If node is the left child.
        {
          // Replace the parent's left child as the node's left child.
          parent.setLeftChild(rootNode.getLeftChild());
        }
        else // If node is the right child.
        {
          // Replace the parent's right child as the node's right child.
          parent.setRightChild(rootNode.getLeftChild());
        }
      }
    } // end first if
  } // end removeFromRoot

  /** Returns a string with the tree in preorder list.
      @return  A string with integers in a preorder list. */
  public String getPreorder()
  {
    return getPreorder(root);
  } // end getPreorder

  /** Private method for the getPreorder method.
      @param node  The node to start the traversal from.
      @return  A string with integers in a preorder list. */
  private String getPreorder(BinaryNode<T> node)
  {
    String output = "";
    if(node != null)
    {
      output += node.getData() + " ";
      output += getPreorder(node.getLeftChild());
      output += getPreorder(node.getRightChild());
    }
    return output;
  } // end private getPreorder

  /** Returns a string with the tree in inorder list.
      @return  A string with integers in an inorder list. */
  public String getInorder()
  {
    return getInorder(root);
  } // end getInorder

  /** Private method for the getInorder method.
      @param node  The node to start the traversal from.
      @return  A string with integers in a inorder list. */
  private String getInorder(BinaryNode<T> node)
  {
    String output = "";
    if(node != null)
    {
      output += getInorder(node.getLeftChild());
      output += node.getData() + " ";
      output += getInorder(node.getRightChild());
    }
    return output;
  } // end private getInorder

  /** Returns a string with the tree in postorder list.
      @return  A string with integers in a postorder list. */
  public String getPostorder()
  {
    return getPostorder(root);
  } // end getPostorder

  /** Private method for the getPostorder method.
      @param node  The node to start the traversal from.
      @return  A string with integers in a postorder list. */
  private String getPostorder(BinaryNode<T> node)
  {
    String output = "";
    if(node != null)
    {
      output += getPostorder(node.getLeftChild());
      output += getPostorder(node.getRightChild());
      output += node.getData() + " ";
    }
    return output;
  } // end private getPostorder

  /** Get predecessor of an entry.
      @param entry  The entry to get predecessor of.
      @return  The predecessor of the specified entry. */
  public T getPredecessor(T entry)
  {
    T predecessor = null;
    BinaryNode<T> node = findNode(root, entry);
    if((!isEmpty()) && (node != null)) // If the tree is not empty and the entry exists.
    {
      if(node.isLeaf())
      {
        // The left most node cannot have a predecessor.
        if(root.getLeftMostData() != node.getData())
        {
          BinaryNode<T> parent = findParent(root, node);
          predecessor = getPredecessorOfLeaf(parent, node).getData();
        }
      }
      else if(node == root) // If node is the root.
      {
        if(node.getLeftChild() != null) // Only if left child of the root node exists.
        {
          predecessor = node.getLeftChild().getRightMostData();
        }
      }
      else // Root is a not leaf node nor the root.
      {
        predecessor = node.getLeftChild().getRightMostData();
      }
    }
    return predecessor;
  } // end getPredecessor

  /** Gets the predecessor of a leaf node. Private method of getPredecessor method.
      @param parent  The parent node of the child param node.
      @param child  The node to check if it is right child of the parent param node. */
  private BinaryNode<T> getPredecessorOfLeaf(BinaryNode<T> parent, BinaryNode<T> child)
  {
    if(parent.getRightChild() == child) // If this condition is met, the predecessor is found.
    {
      return parent; // Return the predecessor.
    }
    else
    {
      return getPredecessorOfLeaf(findParent(root, parent), parent); // Keep traversing until condition is met.
    }
  } // end getPredecessorOfLeaf

  /** Get successor of an entry.
      @param entry  The entry to get successor of.
      @return  The successor of the specified entry. */
  public T getSuccessor(T entry)
  {
    T successor = null;
    BinaryNode<T> node = findNode(root, entry);
    if((!isEmpty()) && (node != null)) // If the tree is not empty and the entry exists.
    {
      if(node.isLeaf())
      {
        // The right most node cannot have a successor.
        if(root.getRightMostData() != node.getData())
        {
          BinaryNode<T> parent = findParent(root, node);
          successor = getSuccessorOfLeaf(parent, node).getData();
        }
      }
      else if(node == root) // If node is the root.
      {
        if(node.getRightChild() != null) // Only if right child of the root node exists.
        {
          successor = node.getRightChild().getLeftMostData();
        }
      }
      else // Root is a not leaf node nor the root.
      {
        successor = node.getRightChild().getLeftMostData();
      }
    }
    return successor;
  } // end getSuccesor

  /** Gets the successor of a leaf node. Private method of getSuccessor method.
      @param parent  The parent node of the child param node.
      @param child  The node to check if it is the left child of the parent param node. */
  private BinaryNode<T> getSuccessorOfLeaf(BinaryNode<T> parent, BinaryNode<T> child)
  {
    if(parent.getLeftChild() == child) // If this condition is met, the successor is found.
    {
      return parent; // Return the predecessor.
    }
    else
    {
      return getSuccessorOfLeaf(findParent(root, parent), parent); // Keep traversing until condition is met.
    }
  } // end getSuccessorOfLeaf
} // end of BinarySearchTree
