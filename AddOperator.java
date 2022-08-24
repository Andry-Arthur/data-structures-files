
public class AddOperator extends Operator 
{
	public AddOperator() 
	{
		precedence = 1;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		return b + a;
	}
}
