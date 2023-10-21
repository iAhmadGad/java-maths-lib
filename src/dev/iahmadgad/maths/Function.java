package dev.iahmadgad.maths;

import java.util.ArrayList;

public class Function 
{
	private String functionString;
	
	public Function(String functionString)
	{
		this.functionString = functionString;
	}
	
	public Function(Maths function)
	{
		
	}
	
	public String getFunctionString()
	{
		return functionString;
	}
	
	public double get(String x)
	{
		return Solver.getValue(functionString.replace("x", '(' + x + ')'));
	}
	
	public double get(double x)
	{
		return Solver.getValue(functionString.replace("x", '(' + String.valueOf(x) + ')'));
	}
	
	public double get(int x)
	{
		return Solver.getValue(functionString.replace("x", '(' + String.valueOf(x) + ')'));
	}
	
	public String getType()
	{
		return "";
	}
	
	public String getDomain()
	{
		return "";
	}
	
	public String getRange()
	{
		return "";
	}
	
	public Object lim(int x)
	{
		return "";
	}
}
