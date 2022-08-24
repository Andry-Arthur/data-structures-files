
public class LeftParenOperator extends Operator 
{
	public LeftParenOperator() 
	{
		precedence = -2;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		throw new UnsupportedOperationException();
	}
}
