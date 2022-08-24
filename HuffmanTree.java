import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class HuffmanTree 
{
	private TreeMap<Character, Integer> map;
	private PriorityQueue<HNode> pq;
	private HNode root;
	
	public HuffmanTree(TreeMap<Character, Integer> frequencies) 
	{
		map = frequencies;
		pq = new PriorityQueue<HNode>();	
		Set<Map.Entry<Character, Integer>> setMap = map.entrySet();
		
		for(Map.Entry<Character, Integer> entry: setMap) {
			HNode node = new HNode(entry.getKey(), entry.getValue());
			pq.add(node);
		}
		
		int n = pq.size();
		while(n > 1) {
			HNode h1 = pq.poll();
			HNode h2 = pq.poll();
			HNode h3 = new HNode(h1, h2);
			
			pq.add(h3);
			n--;
		}
		
		root = pq.poll();
	}
	
	public String encodeLoop(char symbol) 
	{
		HNode curr = root;
		String result = "";
		
		while(!curr.isLeaf()) {
			HNode left = curr.getLeft();
			HNode right = curr.getRight();
			
			if(left.getSymbol().contains(""+symbol)) {
				result = result + 0;
				curr = left;
			}
			else if(right.getSymbol().contains(""+symbol)) {
				result = result + 1;
				curr = right;
			}
			else {
				return null;
			}
		}
		
		return result;
	}
	
	public String encode(char symbol) 
	{	
		return encode(symbol, root);
	}
	
	public String encode(char symbol, HNode curr) 
	{
		HNode left = curr.getLeft();
		HNode right = curr.getRight();
		if(curr.isLeaf()) {
			return "";
		}
		else if(left.getSymbol().contains(""+symbol)) {
			return "0" + encode(symbol, left);
		}
		else if(right.getSymbol().contains(""+symbol)) {
			return "1" + encode(symbol, right);
		}
		else {
			throw new NoSuchElementException();
		}
	}
	
	public char decode(String code) 
	{
		HNode curr = root;
		
		for(int i = 0; i < code.length(); i++) {
			if(code.charAt(i) == '0') {
				curr = curr.getLeft();
			}
			else { //if it's not '0' it's '1'
				curr = curr.getRight();
			}
		}
		
		return curr.getSymbol().charAt(0);
	}
	
	public void writeCode(char symbol, BitOutputStream stream) throws IOException 
	{
		HNode curr = root;
		
		while(!curr.isLeaf()) {
			if(curr.getLeft().contains(symbol)) {
				curr = curr.getLeft();
				stream.writeBit(0);
			}
			else {
				curr = curr.getRight();
				stream.writeBit(1);
			}
		}
	}
	
	public char readCode(BitInputStream stream) throws IOException 
	{
		HNode curr = root;
		
		while(stream.hasNext() && !curr.isLeaf()) {
			if(stream.readBit() == 0) {
				curr = curr.getLeft();
			}
			else {
				curr = curr.getRight();
			}
		}
		
		if(!curr.isLeaf()) {
			return '\0';
		}
		return curr.getSymbol().charAt(0);
	}
}
