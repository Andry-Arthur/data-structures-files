
public class PowerOperator extends Operator 
{
	public PowerOperator() 
	{
		precedence = 3;
	}

	@Override
	public double evaluate(double a, double b) 
	{
		return Math.pow(b, a);
	}
}
