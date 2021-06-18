/**
 * 
 */
package com.ss.may.jb2;

import java.math.BigDecimal;
import java.util.Scanner;
/**
 * @author lukej
 *
 */
public class multipleInputs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please enter 3 numbers");
		Scanner myObj = new Scanner(System.in);
		BigDecimal sum = new BigDecimal("0");
		double placeHolder = 0;
		for(int i=0;i < 3;i++) {
			String line = myObj.nextLine();
			try {
				placeHolder = Double.parseDouble(line);
				sum = sum.add(BigDecimal.valueOf(placeHolder));
//				System.out.println("value: " + sum  + " entered : " + placeHolder + " conversion: " + BigDecimal.valueOf(placeHolder));
			}
			catch(NumberFormatException ex){
				System.out.println("Please enter a number");
				i = i - 1;
			}
		}
		System.out.println("Sum is: " + sum);
	}

}
