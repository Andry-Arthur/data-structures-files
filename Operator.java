
public abstract class Operator 
{
	protected int precedence; //change to private
	
	public Operator() 
	{
	}
	
	public int getPrecedence() 
	{
		return precedence;
	}
	
	public abstract double evaluate(double a, double b);
}
