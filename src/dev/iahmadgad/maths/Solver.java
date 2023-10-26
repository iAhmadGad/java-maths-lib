package dev.iahmadgad.maths;

import java.util.ArrayList;

public class Solver
{
	public static Object getEnhancedValue(String sentence)
	{
		double value = getValue(sentence);
		if(value - (int) value == 0.0) return (int) value;
		else return value;
	}
	
	public static Object getEnhancedValue(ArrayList<String> tokens)
	{
		double value = getValue(tokens);
		if(value - (int) value == 0.0) return (int) value;
		else return value;
	}
	
	public static double getValue(String sentence)
	{
		ArrayList<String> listVal = Tokener.getTokens(sentence);
		return Double.parseDouble(solve(listVal));
	}
	
	public static double getValue(ArrayList<String> tokens)
	{
		return Double.parseDouble(solve(tokens));
	}
	
	private static String solve(ArrayList<String> tokens)
	{
		for(int i = 0; i < tokens.size(); i++)
		{
			if(isNestedOperation(tokens.get(i)))
			{
				String currentOperation = String.valueOf(getValue(tokens.get(i).substring(1, tokens.get(i).length() - 1)));
				tokens.set(i, currentOperation);
			}
		}
		for(int i = 0; i < tokens.size(); i++)
		{
			if(isFunction(tokens.get(i)))
			{
				String currentOperation = String.valueOf(operateFunction(tokens.get(i)));
				tokens.set(i, currentOperation);
			}
		}
		for(int i = 0; i < tokens.size() && tokens.size() > 1; i++)
		{
			if(isFirstOrderOperator(tokens.get(i)) && operandsNumber(tokens.get(i)) == 2)
			{
				String currentOperation = String.valueOf(operate(Double.parseDouble(tokens.get(i - 1)), tokens.get(i), Double.parseDouble(tokens.get(i + 1))));
				tokens.set(i - 1, currentOperation);
				tokens.remove(i);
				tokens.remove(i);
				i -= 2;
			}
			else if(isFirstOrderOperator(tokens.get(i)) && operandsNumber(tokens.get(i)) == 1)
			{
				String currentOperation = String.valueOf(operate(tokens.get(i), Double.parseDouble(tokens.get(i + 1))));
				tokens.set(i, currentOperation);
				tokens.remove(i + 1);
				i--;
			}
		}
		for(int i = 0; i < tokens.size() && tokens.size() > 1; i++)
		{
			if(isSecondOrderOperator(tokens.get(i)))
			{
				String currentOperation = String.valueOf(operate(Double.parseDouble(tokens.get(i - 1)), tokens.get(i), Double.parseDouble(tokens.get(i + 1))));
				tokens.set(i - 1, currentOperation);
				tokens.remove(i);
				tokens.remove(i);
				i -= 2;
			}
		}
		for(int i = 0; i < tokens.size() && tokens.size() > 1; i++)
		{
			if(isThirdOrderOperator(tokens.get(i)))
			{
				String currentOperation = String.valueOf(operate(Double.parseDouble(tokens.get(i - 1)), tokens.get(i), Double.parseDouble(tokens.get(i + 1))));
				tokens.set(i - 1, currentOperation);
				tokens.remove(i);
				tokens.remove(i);
				i -= 2;
			}
		}
		return tokens.get(0);
	}
	
	private static byte operandsNumber(String operand)
	{
		if(operand.compareTo("!") == 0) return 1;
		else return 2;
	}
	
	private static double operate(double firstOperand, String operator, double secondOperand)
	{
		if(operator.compareTo("+") == 0) return firstOperand + secondOperand;
		else if (operator.compareTo("-") == 0) return firstOperand - secondOperand;
		else if(operator.compareTo("*") == 0 || operator.compareTo("x") == 0) return firstOperand * secondOperand;
		else if(operator.compareTo("/") == 0 || operator.compareTo("÷") == 0) return firstOperand / secondOperand;
		else if(operator.compareTo("^") == 0) return Math.pow(firstOperand, secondOperand);
		return 0;
	}
	
	private static double operate(String operator, double operand)
	{
		if(operator.compareTo("!") == 0) return Standard.factorial(operand);
		return 0;
	}
	
	private static double operateFunction(String function)
	{
	    if(function.contains("pow")) return Math.pow(getValue(function.substring(4).split(",")[0]), getValue(function.substring(4).split(",")[1]));
	    else if(function.contains("sqrt")) return Math.sqrt(getValue(function.substring(5)));
		else if(function.contains("cbrt")) return Math.cbrt(getValue(function.substring(5)));
		else if(function.contains("sin")) return Math.sin(Math.toRadians(getValue(function.substring(4))));
		else if(function.contains("cos")) return Math.cos(Math.toRadians(getValue(function.substring(4))));
		else if(function.contains("tan")) return Math.tan(Math.toRadians(getValue(function.substring(4))));
		else if(function.compareTo("rnd") == 0) return Standard.random();
		else if(function.contains("rnd")) return Standard.random(getValue(function.substring(4).split(",")[0]), getValue(function.substring(4).split(",")[1]));
		return 0;
	}
	
	private static boolean isNestedOperation(String s)
	{
		if(s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') return true;
		return false;
	}
	
	private static boolean isFunction(String s)
	{
		if(s.contains("pow")|| s.contains("sqrt")|| s.contains("cbrt")|| s.contains("sin")|| s.contains("cos")|| s.contains("tan")|| s.contains("rn")) return true;
		return false;
	}
	
	private static boolean isFirstOrderOperator(String s)
	{
		if(s.compareTo("^") == 0 || s.compareTo("!") == 0) return true;
		return false;
	}
	
	private static boolean isSecondOrderOperator(String s)
	{
		if(s.compareTo("*") == 0 || s.compareTo("x") == 0 || s.compareTo("/") == 0 ||  s.compareTo("÷") == 0) return true;
		return false;
	}
	
	private static boolean isThirdOrderOperator(String s)
	{ 
		if(s.compareTo("+") == 0 || s.compareTo("-") == 0) return true;
		return false;
	}
}
