
public class RightParenOperator extends Operator 
{
	public RightParenOperator() 
	{
		precedence = -1;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		throw new UnsupportedOperationException();
	}
}
