package appOne;
/**
 * Conversion Application
 * @author Marietta Asemwota 
 * @Date 9/28/2017
 */

public class ConversionTester {
	
	public static void main(String [] args)
	{
		Conversion tester = new Conversion(); 
		//System.out.println(Integer.toBinaryString(-12));
		//System.out.println(tester.range(-32) + " bits");
		String binary = tester.convertDecimalToBinary(-30);
		System.out.println(binary);
		
		//double decimal1 = tester.binaryToDecimal(binary.substring(1)); 
		//System.out.println(decimal1);
		//double decimal = tester.binaryToDecimal(binary); 
		//System.out.println(decimal); 
	}
}