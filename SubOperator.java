
public class SubOperator extends Operator 
{
	public SubOperator() 
	{
		precedence = 1;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		return b - a;
	}
}
