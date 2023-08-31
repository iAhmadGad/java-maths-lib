package iahmadgad.math;

import java.util.ArrayList;

/**
 * Solve Class
 * @author iAhmadGad
 * Java Math Parser
 * @version 0.1
 *
 */

public class Solve
{
	public Solve(String sentence)
	{
		ArrayList<String> elements = split(sentence);
		if(!isValid(elements)) return;
		double temp;
		
		while(elements.contains("cos") || elements.contains("sin") || elements.contains("tan"))
		{
			for(int i = 0; i < elements.size() - 1; i++)
			{
				if(elements.get(i).compareTo("sin") == 0)
				{
					temp = Math.sin(Math.toRadians(Double.parseDouble(elements.get(i + 1)))) ;
					elements.set(i, Double.toString(temp));
					elements.remove(i + 1);
				}
				
				else if(elements.get(i).compareTo("cos") == 0)
				{
					temp = Math.cos(Math.toRadians(Double.parseDouble(elements.get(i + 1)))) ;
					elements.set(i, Double.toString(temp));
					elements.remove(i + 1);
				}
				
				else if(elements.get(i).compareTo("tan") == 0)
				{
					temp = Math.tan(Math.toRadians(Double.parseDouble(elements.get(i + 1)))) ;
					elements.set(i, Double.toString(temp));
					elements.remove(i + 1);
				}
			}
		}
		
		while(elements.contains("^") || elements.contains("sqrt") || elements.contains("cbrt") || elements.contains("\u221A"))
		{
			for(int i = 0; i < elements.size() - 1; i++)
			{
				if(elements.get(i).compareTo("^") == 0)
				{
					temp = Math.pow(Double.parseDouble(elements.get(i - 1)), Double.parseDouble(elements.get(i + 1))) ;
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("sqrt") == 0 || (i != 0 && elements.get(i).compareTo("\u221A") == 0 && elements.get(i - 1).compareTo("3") != 0) || (i == 0 && elements.get(i).compareTo("\u221A") == 0))
				{
					temp = Math.sqrt(Double.parseDouble(elements.get(i + 1)));
					elements.remove(i);
					elements.set(i, Double.toString(temp));
				}
				else if(elements.get(i).compareTo("cbrt") == 0)
				{
					temp = Math.cbrt(Double.parseDouble(elements.get(i + 1)));
					elements.remove(i);
					elements.set(i, Double.toString(temp));
				}
				else if(elements.get(i).compareTo("\u221A") == 0 && elements.get(i - 1).compareTo("3") == 0)
				{
					temp = Math.cbrt(Double.parseDouble(elements.get(i + 1)));
					elements.set(i, Double.toString(temp));
					elements.remove(i - 1);
					elements.remove(i);
				}
			}
		}
		
		while(elements.contains("*") || elements.contains("/") || elements.contains("%"))
		{
			for(int i = 0; i < elements.size() - 1; i++)
			{
				if(elements.get(i).compareTo("*") == 0)
				{
					temp = Double.parseDouble(elements.get(i - 1)) * Double.parseDouble(elements.get(i + 1));
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("/") == 0)
				{
					temp = Double.parseDouble(elements.get(i - 1)) / Double.parseDouble(elements.get(i + 1));
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("%") == 0)
				{
					temp = Double.parseDouble(elements.get(i - 1)) % Double.parseDouble(elements.get(i + 1));
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
			}
		}
		while(elements.size() != 1)
		{
			for(int i = 0; i < elements.size() - 1; i++)
			{
				if(elements.get(i).compareTo("+") == 0)
				{
					temp = Double.parseDouble(elements.get(i - 1)) + Double.parseDouble(elements.get(i + 1));
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("-") == 0)
				{
					temp = Double.parseDouble(elements.get(i - 1)) - Double.parseDouble(elements.get(i + 1));
					elements.set(i - 1, Double.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
			}
		}
		value = Double.parseDouble(elements.get(0));
	}
	
	private static double value;
	
	public double getValue()
	{
		return value;
	}
	
	private static ArrayList<String> split(String raw)
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		int index = 0;
		for(int i = 0; i < raw.length(); i++)
		{
		    if(raw.charAt(i) == '+' || raw.charAt(i) == '-' || raw.charAt(i) == '*' || raw.charAt(i) == '/' || raw.charAt(i) == '%' ||raw.charAt(i) == '^' || raw.charAt(i)  == 0x221A || raw.charAt(i)  == 0x221A)
			{
				list.add("");
				index++;
				list.set(index, list.get(index) + raw.charAt(i));
				list.add("");
				index++;
			}
			else if (Character.isAlphabetic(raw.charAt(i)))
			{
				list.add("");
				index++;
				list.set(index, list.get(index) + raw.charAt(i));
				i++;
				while(Character.isAlphabetic(raw.charAt(i)))
				{
					list.set(index, list.get(index) + raw.charAt(i));
					i++;
				}
				list.add("");
				index++;
				list.set(index, list.get(index) + raw.charAt(i));
			}
			else list.set(index, list.get(index) + raw.charAt(i));
		}
		return filter(list);
	}
	
	private static ArrayList<String> filter(ArrayList<String> split)
	{
		for(int i = 0; i < split.size(); i++)
		{
			if(split.get(i).compareTo("") == 0) split.remove(i);
		}
		return split;
	}
	
	private static boolean isValid(ArrayList<String> split)
	{
		for(int i = 0; i < split.size(); i++)
		{
			String current = split.get(i);
			if(current.compareTo("+") != 0 && current.compareTo("-") != 0 && current.compareTo("*") != 0 && current.compareTo("/") != 0 && current.compareTo("%") != 0 && current.compareTo("sqrt") != 0 && current.compareTo("cbrt") != 0 && current.compareTo("\u221A") != 0 && current.compareTo("cos") != 0 && current.compareTo("sin") != 0 && current.compareTo("tan") != 0 && current.compareTo("rn") != 0 && !TypeChecker.isDouble(current) && !TypeChecker.isInteger(current)) return false;
			
		}
		return true;
	}
}
