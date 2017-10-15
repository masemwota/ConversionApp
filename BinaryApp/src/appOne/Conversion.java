package appOne;
/**
 * Conversion Application
 * @author Marietta Asemwota 
 * @Date 9/28/2017
 */

public class Conversion {
	//variables to store the range of the number
	private double lowerBound; 
	private double upperBound;
	
	
	/*
	 * SIGNBIT = 0 or 1 depending on +/-
	 * this case 0
	 * 8/2 = 4 r 0
	 * 4/2 = 2 r 0
	 * 2/2 = 1 r 0
	 * 1/2 = 0 r 1
	 * = *SIGNBIT*1000
	 */
	
	/**
	 * Decimal to binary returns in string to preserve the 0's
	 * @param number - the decimal number to convert to 2's complement binary
	 * @return a String representation of the converted number to preserve the 0's
	 */
	public String convertDecimalToBinary(double number) {
		
		String binary = ""; 
		
		if(number < 0)
		{
			return negativeConvert(number); 
		}
		else if (number > 0)
		{
			return positiveConvert(number); 
		}
		return "0000"; 
		
	}
	
	
	public String positiveConvert(double number)
	{
		//to hold the converted number
		String binary = ""; 
		
		//the first digit of the converted number will be the signed bit
		int signBit;
		
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
		boolean isNegative = false; 
		if(number < 0)
		{
			isNegative = true; 
			//-1 to account for the fact that the positive part of the range is one less
			number = Math.abs(number);  
		}
		
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
		
		if((isNegative) && (number % 8 == 0))
		{
			result--; 
		}
		
		upperBound = Math.pow(2, result-1)-1;  
		lowerBound = upperBound+1; 
		
		System.out.println("-" + lowerBound + ", " + upperBound);
		return result; 
	}
	
	/*
	
	/**
	 * This method takes a negative decimal and turns it into a positive decimal 
	 * that is ready to be converted to binary
	 * @param number
	 * @return
	 
	public double compute(double number)
	{
		//formula: compute x = abs(lower bound) - abs(n)
		double x; 
		double lower = Math.abs(lowerBound); 
		double num = Math.abs(number); 
		
		x = lower - num; 
		return x; 
	}
	
	*/
	
	public String negativeConvert(double number) {
        int numBits = (int)range(number);
        double toConvert = Math.abs(lowerBound) - Math.abs(number);
        String converted = convertDecimalToBinary(toConvert);
        converted = "1" + converted;
        return converted;
    }
	
	public double binaryToDecimal(String binary)
	{
		int sigInt = Integer.parseInt(binary.substring(0)); 
		
		if(sigInt == 1)
		{
			//return the negative conversion 
			return negativeBinaryToDecimal(binary); 
		}
		
		else if (sigInt == 0)
		{
			//return the positive decimal conversion
			return positiveBinaryToDecimal(binary); 
		}
		
		else 
		{
			return 0; 
		}
	}
	
	public double positiveBinaryToDecimal(String number) {
		//takes in a binary as a string
		double toReturn = 0;
		int i = 0;
		
		//while the number doesn't equal the empty string 
		while (!number.equals("")) {
			
			//if the last digit is 1
			if (number.substring(number.length()-1, number.length()).equals("1")) 
			{
				//do 2 to the power of something which gets rid of it 
				toReturn += Math.pow(2, i); //does 2 to the power of 1
				number = number.substring(0, number.length()-1);
				i++;
			} 
			
			else 
			{ //0 -- get rid of the last digit and increment i 
				number = number.substring(0, number.length()-1);
				i++;
			}
		}
		return toReturn;
		
	}
	
	public double negativeBinaryToDecimal(String number)
	{
		return 0; 
	}
}