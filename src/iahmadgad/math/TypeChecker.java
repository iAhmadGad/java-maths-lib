package iahmadgad.math;

/**
 * TypeChecker Class
 * @author iAhmadGad
 * Java Math Parser
 * @version 0.1
 *
 */

public class TypeChecker 
{
	public TypeChecker()
	{
		
	}
	
	public static boolean isDouble(String variable)
	{
		int point = 0;
		for(int i = 0; i < variable.length(); i++)
		{
			if(variable.charAt(i) == '.' && point == 0) point = 1;
			else if((!Character.isDigit(variable.charAt(i)) && variable.charAt(i) != '.') || variable.charAt(i) == '.' && point == 1) return false;
		}
		if(point == 0) return false;
		return true;
	}
	
	public static boolean isInteger(String variable)
	{
		for(int i = 0; i < variable.length(); i++)
		{
			if(!Character.isDigit(variable.charAt(i))) return false;
		}
		return true;
	}
}
