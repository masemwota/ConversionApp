package appOne;

/**
 * Binary android app conversions
 * September 28, 2017
 * @author monsi
 *
 */
public class Conversion {
	//variables to store the range
	private double lowerBound; 
	private double upperBound; 
	private double numberOfBits; 
	
	/**
	 * decimal to binary
	 * @param number the decimal number to convert to 2's complement binary
	 * @return a String representation of the converted number to preserve the 0's
	 */
	public String convertDecimalToBinary(double number) {
		//append the signed bit to the beginning of the converted string and return it
		range(number); 
		if(number < 0)
		{
			String toReturn = negativeConvert(number);
			if (number % 8 == 0) toReturn = "1" + toReturn.substring(1); //accommodation for edge cases
			return "1" + toReturn; 
		}
		else if (number > 0)
		{
			return "0"+ positiveConvert(number); 
		}
		return "0000"; 
		
	}
	
	/**
	 * returns a string representation of the positive number in binary
	 * @param number a positive number
	 * @return the number in binary
	 */
	public String positiveConvert(double number)
	{
		System.out.println("number:  " + number);
		//to hold the converted number
		String binary = ""; 
	
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
			
			number = result;
		}
		
		while (binary.length() != numberOfBits) {
			binary = "0" + binary;
		}
		return binary; 
	}
	
	/**
	 * returns a string representation of the negative number in binary
	 * @param number a negative number
	 * @return the number in binary
	 */
	public String negativeConvert(double number) {
        //int numBits = (int)range(number);
        double toConvert = Math.abs(Math.abs(lowerBound) - Math.abs(number));
        String converted = positiveConvert(toConvert);
        return converted;
    }
	
	/**
	 * calculates how many bits are needed
	 * @param number the decimal number to convert to 2's complement binary
	 * @return how many bits are needed to represent the number in 2's complement binary
	 */
	public double range(double number) {
		boolean isNegative = false; 
		if(number < 0) {
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
		if(result % 1 == 0) {
			//this means it is an exact power of two, and therefore is an edge case 
			//the upper bound typically doesn't allow the last power to be part of the range
			result ++; 
		}
		
		else {
//			result = Math.round(result); 
			result = Math.floor(result);
			result++;
		}
		
		System.out.println("result in range: " + result);
//		result++; 
		numberOfBits = result;
		
		if((isNegative) && (number % 8 == 0)) {
			result--; 
		}
		
		if (isNegative) upperBound = Math.pow(2, result)-1;
		else upperBound = Math.pow(2, result-1)-1;  
		lowerBound = upperBound+1; 
		
//		System.out.println("-" + lowerBound + ", " + upperBound);
		return result; 
	}
	
	public void binaryRange(int bits)
	{
		//given the amount of bits, calculate the range for the number
		upperBound = Math.pow(2, bits-1)-1;  
		lowerBound = upperBound+1; 
	}
	
	
	public double binaryToDecimal(String number) {
		int length = number.length();
		binaryRange(length);
		if (number.substring(0,1).equals("1")) { //sigbit if 1. negative
			System.out.println("negative");
			return negativeBinaryToDecimal(number);
		} else if (number.substring(0,1).equals("0")){ //sigbit is 0. positive
			System.out.println("positive");
			return positiveBinaryToDecimal(number);
		} else {
			return 0;
		}
	}
	
	public double negativeBinaryToDecimal(String number) {
        String withoutSign = number.substring(1);
        System.out.println("withoutSign: " + withoutSign);
        double intermediate = positiveBinaryToDecimal(withoutSign);
        System.out.println("intermediate: " + intermediate);
        if (intermediate % 8 == 0) return 0-intermediate; 
        double x = Math.abs(lowerBound) - intermediate;
        System.out.println("x: " + x);
        System.out.println("lb: " + lowerBound);
        return 0-x;
    }
	

	public double positiveBinaryToDecimal(String number) {
		double toReturn = 0;
		int i = 0;
		while (!number.equals("")) {
			if (number.substring(number.length()-1, number.length()).equals("1")) {
				toReturn += Math.pow(2, i);
				number = number.substring(0, number.length()-1);
				i++;
			} else { //0
				number = number.substring(0, number.length()-1);
				i++;
			}
		}
		return toReturn;
		
	}
	
	public boolean isPowerOfTwo(double number) {
		double baseTen = Math.log10(number); 
		double logTwo = Math.log10(2);
		double result = baseTen / logTwo;
		if (Math.round(number) == number) {
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}
}