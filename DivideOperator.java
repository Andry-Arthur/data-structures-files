
public class DivideOperator extends Operator 
{
	public DivideOperator() 
	{
		precedence = 2;
	}
	
	@Override
	public double evaluate(double a, double b) 
	{
		return b/a;
	}
}
