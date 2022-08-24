import java.util.Comparator;

public class HNodeComparator implements Comparator<HNode>
{
	public int compare(HNode n1, HNode n2) 
	{
		int freq1 = n1.getFreq();
		int freq2 = n2.getFreq();
		if(freq1 > freq2) {
			return 1;
		}
		else if(freq1 < freq2) {
			return -1;
		}
		else {
			String s1 = n1.getSymbol();
			String s2 = n2.getSymbol();
			if (s1.compareTo(s2) < 0) {
				return -1;
			}
			else if (s1.compareTo(s2) > 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
