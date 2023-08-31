package iahmadgad.math;

import java.util.ArrayList;

public class Solve
{
	public static String getValue(String sentence)
	{
		ArrayList<String> elements = split(sentence);
		int temp;
		while(elements.contains("*") || elements.contains("/"))
		{
			for(int i = 0; i < elements.size() - 1; i++)
			{
				if(elements.get(i).compareTo("*") == 0)
				{
					temp = Integer.parseInt(elements.get(i - 1)) * Integer.parseInt(elements.get(i + 1));
					elements.set(i - 1, Integer.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("/") == 0)
				{
					temp = Integer.parseInt(elements.get(i - 1)) / Integer.parseInt(elements.get(i + 1));
					elements.set(i - 1, Integer.toString(temp));
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
					temp = Integer.parseInt(elements.get(i - 1)) + Integer.parseInt(elements.get(i + 1));
					elements.set(i - 1, Integer.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
				else if(elements.get(i).compareTo("-") == 0)
				{
					temp = Integer.parseInt(elements.get(i - 1)) - Integer.parseInt(elements.get(i + 1));
					elements.set(i - 1, Integer.toString(temp));
					elements.remove(i);
					elements.remove(i);
				}
			}
		}
		return elements.get(0);
	}
	
	public static ArrayList<String> split(String raw)
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		int index = 0;
		for(int i = 0; i < raw.length(); i++)
		{
			if(raw.charAt(i) == '+' || raw.charAt(i) == '-' || raw.charAt(i) == '*' || raw.charAt(i) == '/')
			{
				list.add("");
				index++;
				list.set(index, Character.toString(raw.charAt(i)));
				list.add("");
				index++;
			}
			else list.set(index, list.get(index) + raw.charAt(i));
		}
		return list;
	}
}
