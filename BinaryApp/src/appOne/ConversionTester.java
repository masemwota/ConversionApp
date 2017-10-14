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
		System.out.println(tester.range(1));
		System.out.println(tester.convertDecimalToBinary(12));
	}
}