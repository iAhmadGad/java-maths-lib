package dev.iahmadgad.maths;

import java.util.ArrayList;

import dev.iahmadgad.maths.util.Constants;

public class Function 
{
	private String functionString;
	
	public Function(String functionString)
	{
		this.functionString = functionString;
	}
	
	public String getFunctionString()
	{
		return functionString;
	}
	
	public double get(int x)
	{
		return ValueDefiner.getValue(functionString.replace("x", '(' + String.valueOf(x) + ')'));
	}
	
	public double get(double x)
	{
		return ValueDefiner.getValue(functionString.replace("x", '(' + String.valueOf(x) + ')'));
	}
	
	public double get(String x)
	{
		return ValueDefiner.getValue(functionString.replace("x", '(' + x + ')'));
	}
	
	public double get(String parameters, String values)
	{
		String params[] = parameters.split(","), vals[] = values.split(","), functionString = this.functionString;
		for(int i = 0; i < params.length; i++)
		{
			functionString = functionString.replace(params[i], '(' + vals[i] + ')');
		}
		return ValueDefiner.getValue(functionString);
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
