public class HNode 
{
	private HNode left;
	private HNode right;
	private String symbols;
	private int freq;
	
	public HNode(char c, int f) 
	{
		left = null;
		right = null;
		symbols = symbols + c;
		freq = freq + f;
	}
	
	public HNode(HNode left, HNode right) 
	{
		this.left = left;
		this.right = right;
		symbols = left.symbols + right.symbols;
		freq = left.freq + right.freq;
	}
	
	public boolean isLeaf() 
	{
		return (this.left == null) && (this.right == null);
	}
	
	public boolean contains(char ch) 
	{
		String str = "" + ch;
		return symbols.contains(str);
	}
	
	public int getFreq() 
	{
		return freq;
	}
	
	public String getSymbol() 
	{
		return symbols;
	}
	
	public HNode getLeft() 
	{
		return left;
	}
	
	public HNode getRight() 
	{
		return right;
	}
}
