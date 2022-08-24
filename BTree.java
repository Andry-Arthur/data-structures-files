import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BTree<E> 
{
	public Node<E> root;
	public Comparator<E> comp;
	public int order;

	public BTree(int theOrder, Comparator<E> theComp) 
	{
		order = theOrder;
		comp = theComp;
		root = new Node<E>(theOrder, theComp);
	}

	public void add(E item) 
	{
		Node<E> node = findLeaf(root, item);

		node.leafAdd(item);
		
		while(node.hasOverflow()) {
			node.split();
			node = node.parent;
		}
		
		if(root.parent != null) {
			root = root.parent;
		}
	}

	private Node<E> findLeaf(Node<E> curr, E item) 
	{
		while(!curr.isLeaf()) {
			curr = curr.childToFollow(item);
		}
		return curr;
	}

	public boolean contains(E item) 
	{
		return findNode(root, item) != null;
	}

	private Node<E> findNode(Node<E> curr, E item) 
	{
		if(curr.contains(item)) {
			return curr;
		}
		else if(curr.isLeaf()) {
			return null;
		}
		else {
			return findNode(curr.childToFollow(item), item);
		}
	}

	public void inorder(Visitor<E> visitor) 
	{
		inorder(visitor, root);
	}

	private void inorder(Visitor<E> visitor, Node<E> curr) 
	{
		if(curr == null) {
			return;
		}
		else {
			int dataSize = curr.data.size();
			int childSize = curr.children.size();
			for(int i = 0; i < dataSize ; ++i) {
				//
				if(i < childSize) {
					inorder(visitor, curr.children.get(i));
				}
				visitor.visit(curr.data.get(i));
			}
			//
			if(dataSize+1 == childSize) {
				inorder(visitor, curr.children.get(dataSize));
			}	
		}
	}

	public String toStringSorted() 
	{
		StringVisitor<E> visitor = new StringVisitor<>();
		inorder(visitor);
		return visitor.getValue();
	}

	public String toString() 
	{
		String result = "";
		LinkedList<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node<E> curr = queue.poll();
			LinkedList<Node<E>> children = curr.children;
			for(Node<E> child: children) {
				queue.add(child);
			}
			String str = curr.data.toString();
			result = result + " " +  str.replaceAll(",", "");
		}
		result = "[" + result.trim() + "]";
		return result;
	}
}
