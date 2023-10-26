package dev.iahmadgad.maths;

import java.util.ArrayList;

public class Tokener
{
	public static ArrayList<String> getTokens(String sentence)
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		int index = 0;
		int parenthesesCount = 0;
		CurrentParse current = CurrentParse.Default;
		for(int i = 0; i < sentence.length(); i++)
		{
			if(Character.isAlphabetic(sentence.charAt(i)) && current == CurrentParse.Default)
			{
				index++;
				if(index > 0 && (presentsInteger(list.get(index - 1)) || presentsDouble(list.get(index - 1))))
				{
					list.add("*");
					index++;
				}
				current = CurrentParse.Function;
				list.add("");
			}
			else if(sentence.charAt(i) == '(' && current == CurrentParse.Default)
			{
				index++;
				if(index > 0 && (presentsInteger(list.get(index - 1)) || presentsDouble(list.get(index - 1))))
				{
					list.add("*");
					index++;
				}
				current = CurrentParse.BetweenParentheses;
				list.add("");
			}
			if(current == CurrentParse.Function)
			{
				if(sentence.charAt(i) == '(') parenthesesCount++;
				else if(sentence.charAt(i) == ')') parenthesesCount--;
				if((sentence.charAt(i) == ')' || Character.isDigit(sentence.charAt(i))) && parenthesesCount == 0)
				{
					list.add("");
					index++;
					current = CurrentParse.Default;
				}
				else list.set(index, list.get(index) + sentence.charAt(i));
			}
			if(current == CurrentParse.BetweenParentheses)
			{
				if(sentence.charAt(i) == '(') parenthesesCount++;
				else if(sentence.charAt(i) == ')') parenthesesCount--;
				if(sentence.charAt(i) == ')' && parenthesesCount == 0)
				{
					list.set(index, list.get(index) + ')');
					list.add("");
					index++;
					current = CurrentParse.Default;
				}
				else list.set(index, list.get(index) + sentence.charAt(i));
			}
			if(current == CurrentParse.Default)
			{
				if(isOperator(sentence.charAt(i)))
				{
					list.add(Character.toString(sentence.charAt(i)));
					index++;
					list.add("");
					index++;
				}
				else if(isConsonant(sentence.charAt(i)))
				{
					if(index > 0 && (presentsInteger(list.get(index - 1)) || presentsDouble(list.get(index - 1))))
					{
						list.add("*");
						index++;
					}
					list.add(String.valueOf(getConsonantValue(sentence.charAt(i))));
					index++;
					list.add("");
					index++;
				}
				else list.set(index, list.get(index) + sentence.charAt(i));
			}
		}
		while(list.contains("")) list.remove(list.indexOf(""));
		while(list.contains(")")) list.remove(list.indexOf(")"));
		return list;
	}
	
	private static boolean isOperator(char c)
	{
		if(c == '+' || c == '-' || c == '*' || c == '/' || c == '÷' || c == '^' || c == '!') return true;
		return false;
	}
	
	private static boolean isConsonant(char c)
	{
		if(c == 'π') return true;
		return false;
	}
	
	private static double getConsonantValue(char c)
	{
		if(c == 'π') return Math.PI;
		return 1;
	}
	
	/**
	 * Checks if string presents a double value
	 * 
	 * @param string
	 * @return true if it presents a double value & false if it's not
	 */
	protected static boolean presentsDouble(String string)
	{
		int point = 0;
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == '.' && point == 0) point = 1;
			else if((!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.') || string.charAt(i) == '.' && point == 1) return false;
		}
		if(point == 0) return false;
		if(string == "") return false;
		return true;
	}
	
	/**
	 * Checks if string presents an int value
	 * 
	 * @param string
	 * @return true if it presents an int value & false if it's not
	 */
	protected static boolean presentsInteger(String string)
	{
		for(int i = 0; i < string.length(); i++)
		{
			if(!Character.isDigit(string.charAt(i))) return false;
		}
		if(string == "") return false;
		return true;
	}
	
	protected enum CurrentParse
	{
		Default,
		Function,
		BetweenParentheses
	}
}
