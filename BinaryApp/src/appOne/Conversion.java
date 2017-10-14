package appOne;
/**
 * Conversion Application
 * @author Marietta Asemwota 
 * @Date 9/28/2017
 */

public class Conversion {
	
	/**
	 * Decimal to binary returns in string to preserve the 0's
	 * @param number - the decimal number to convert to 2's complement binary
	 * @return a String representation of the converted number to preserve the 0's
	 */
	public String convertDecimalToBinary(double number) {
		
		/*
		 * SIGNBIT = 0 or 1 depending on +/-
		 * this case 0
		 * 8/2 = 4 r 0
		 * 4/2 = 2 r 0
		 * 2/2 = 1 r 0
		 * 1/2 = 0 r 1
		 * = *SIGNBIT*1000
		 */
		
		//first figure out the range for how many bits to use
		if (number == 0) 
			return "0000";
	
		double numberOfBits = range(number);
		
		//the first digit of the converted number will be the signed bit
		int signBit;
		
		//to hold the converted number
		String binary = "";
		
		//in 2s complement, negative numbers have a signed bit of 1
		if (number < 0) 
		{ // number is negative
			signBit = 1; 
		} 
		
		//positive numbers have a signed bit of 0
		else 
		{
			signBit = 0;
		}
		
		//set to arbitrary values
		int result = -1;
		int remainder = -1;
		
		
		while (result != 0) 
		{
			//divide the number by 2 and take the remainder
			result = (int) Math.floor(number/2);
			remainder = (int)number % 2;
			
			//append the remainder to the front because conversion is read backwards
			binary = Integer.toString(remainder) + binary;
			
			/*
			 * Why do we set number equal to result???????? 
			 */
			number = result;
		}
		
		//append the signed bit to the beginning of the converted string and return it
		binary = signBit + binary;
		return binary; 
		
		
	}
	
	
	
	/**
	 * Determine how many bits are needed to represent a number in binary
	 * The range is for signed bits using 2's complement conversion
	 * @param number - the decimal number to convert 
	 * @return how many bits are needed 
	 */
	public double range(double number)
	{
		//we need log base 2 of the number to find the range
		//change of base: need base 10 of original number as well as base converting to
		double baseTen = Math.log10(number); 
		double logTwo = Math.log10(2);
		double result = baseTen / logTwo; //now the number is in base 2
		
		//check if the result is a whole number 
		if(result % 1 == 0)
		{
			//this means it is an exact power of two, and therefore is an edge case 
			//the upper bound typically doesn't allow the last power to be part of the range
			result ++; 
		}
		
		else 
		{
			result = Math.round(result); 
		}
		
		result++; 
		
		return result; 
	}
}