
public class IsPower 
{
	public static boolean isPower(int a, int b) 
	{
		if(a == 1||b==1) {
			return true;
		}
		else if(a == 0) {
			return false;
		}
		else {
			return b%a == 0 && isPower(a/b, b);
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println(isPower(5, 4));
		System.out.println(isPower(2, 4));
		System.out.print(isPower(2, 8));
		
	}
}
