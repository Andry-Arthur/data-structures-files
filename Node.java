import java.util.Comparator;
import java.util.LinkedList;

public class Node<E>
{
	public int order;
	public Node<E> parent;
	public Comparator<E> comp;
	public LinkedList<Node<E>> children;
	public LinkedList<E> data;

	public Node(int theOrder, Comparator<E> theComp) 
	{
		order = theOrder;
		comp = theComp;
		parent = null;
		children = new LinkedList<Node<E>>();
		data = new LinkedList<E>();
	}

	public Node(int theOrder, Comparator<E> theComp, Node<E> left, E item, Node<E> right) 
	{
		this(theOrder, theComp);
		data.add(item);
		children.add(left);
		left.parent = this;
		children.add(right);
		right.parent = this;
	} 

	public Node(int theOrder, Comparator<E> theComp, Node<E> theParent, LinkedList<E> theData, LinkedList<Node<E>> theChildren) 
	{
		order = theOrder;
		comp  = theComp;
		data  = theData;
		parent = theParent;
		children = theChildren;

		for(Node<E> child: children) {
			child.parent = this;
		}
	}

	public boolean hasOverflow() 
	{
		return data.size() > order;
	}

	public boolean isLeaf() 
	{
		return children.isEmpty();
	}

	public Node<E> childToFollow(E item) 
	{
		if(!this.isLeaf()) {
			int i = 0;
			while(i < data.size()) {
				//
				if(comp.compare(data.get(i), item) > 0) {
					break;
				}
				++i;
			}
			return children.get(i);
		}
		return null;
	}

	public void leafAdd(E item) 
	{
		int i = 0;
		//
		if(data.size()==0) {
			data.addFirst(item);
			return;
		}

		while(i < data.size()) {
			//
			if(comp.compare(data.get(i), item) > 0) {
				break;
			}
			++i;
		}
		data.add(i, item);
	}

	public void split() 
	{
		int midInd = data.size()/2;
		//
		if(parent != null) {
			int ind = parent.children.indexOf(this);
			E mid = data.get(midInd);

			parent.data.add(ind, mid);
			Node<E> sibling = new Node<>(order, comp, parent, new LinkedList<E>(), new LinkedList<Node<E>>());
			parent.children.add(ind + 1, sibling);

			sibling.data.addAll(data.subList(midInd+1, data.size()));
			data.subList(midInd, data.size()).clear();

			if(!children.isEmpty()) {
				sibling.children.addAll(children.subList(midInd+1, children.size()));
				children.subList(midInd, children.size()).clear();
			}
		}
		else {
			E mid = data.get(data.size()/2);
			Node<E> sibling = new Node<>(order, comp);
			Node<E> par = new Node<>(order, comp, this, mid, sibling);

			sibling.data.addAll(data.subList(midInd+1, data.size()));

			data.subList(midInd, data.size()).clear();
		}
	}

	public boolean contains(E item) 
	{
		return data.contains(item);
	}
}
