import java.util.Comparator;

public class CountRangeVisitor<E> implements Visitor<E> 
{
	private E lower;
	private E higher;
	private Comparator<E> comp;
	private int count;
	
	public CountRangeVisitor(E low, E high, Comparator<E> c) 
	{
		lower = low;
		higher = high;
		comp = c;
		count = 0;
	}
	
	public void visit(E item) 
	{
		if(comp.compare(item, lower) >= 0 && comp.compare(item, higher) <= 0) {
			count++;
		}
	}
	
	public int getCount() 
	{
		return count;
	}
}
