import java.util.NoSuchElementException;

public class LinkedList<E>
{
	private class Node
	{
		E data;
		Node next;
		//Node previous for Double Linked List
		private Node(E d) 
		{
			data = d;
			next = null;
		}
	}
	
	//member of L.L
	
	private Node head;
	
	//method of L.L
	
	public LinkedList() 
	{
		head = null;
	}
	
	public boolean isEmpty() 
	{
		return head == null;
	}
	
	public void addFirst(E data) 
	{
		Node node = new Node(data);
		node.next = head;
		head = node;
	}
	
	public E getFirst() 
	{
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.data;
	}
	
	public String toString() 
	{
		String result = "";
		
		Node curr = head;
		while(curr != null) {
			result = result + curr.data + " ";
			curr = curr.next;
		}
		
		result = "[" + result.trim() + "]";
		
		return result;
	}
}
