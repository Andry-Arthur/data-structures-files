import java.util.Comparator;

public class StringComparator implements Comparator<String> 
{
	public int compare( String a, String b )
	{
		if (a.compareTo(b) < 0) {
			return -1;
		}
		else if (a.compareTo(b) > 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
