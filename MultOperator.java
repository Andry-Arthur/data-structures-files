
public class MultOperator extends Operator 
{
	public MultOperator() 
	{
		precedence = 2;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		return b * a;
	}
}
