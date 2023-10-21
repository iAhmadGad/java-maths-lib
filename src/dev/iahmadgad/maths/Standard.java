package dev.iahmadgad.maths;

public class Standard
{
	public static double factorial(double n)
	{
		if(n == 1) return n;
		else return n * factorial(n - 1);
	}
	
	public static double random()
	{
		return Integer.MIN_VALUE + (int)(Math.random() * (Integer.MAX_VALUE + 1));
	}
	
	public static double random(double min, double max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}	
}