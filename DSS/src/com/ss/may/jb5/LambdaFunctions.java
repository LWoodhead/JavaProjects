/**
 * 
 */
package com.ss.may.jb5;

import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class LambdaFunctions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LambdaFunctions l = new LambdaFunctions();
//		int num = 38;
//		l.isOdd(num); 
//		l.isPrime(num);
//		l.isPalindrome(780087);
		Scanner myObj = new Scanner(System.in);
		System.out.println("Please enter the number of test cases");
		String line = myObj.nextLine();
		int loopTo = Integer.parseInt(line);
		for(int i = 0; i < loopTo;i++){
			try {
				System.out.println("Please Enter 1 ,2 or 3 followed by the number you want to test");
				line = myObj.nextLine();
				String[] testCase = line.split(" ");
				switch(Integer.parseInt(testCase[0])){
				case 1:
					l.isOdd(Integer.parseInt(testCase[1]));
					break;
				case 2:
					l.isPrime(Integer.parseInt(testCase[1]));
					break;
				case 3:
					l.isPalindrome(Integer.parseInt(testCase[1]));
					break;
				default:
					System.out.println("Please enter 1-3");
				}
			}catch(Exception e){
				System.out.println("Please Enter 1 ,2 or 3 followed by the number you want to test");
			}
		}
		
	}

	public void isOdd(int num) {
		NumberFunction oddLambda = (a) -> {
			if(a % 2 == 1) {
				System.out.println("Odd");
			}else{
				System.out.println("Even");
			}
		};
		oddLambda.run(num);
	}
	
	public void isPrime(int num) {
		NumberFunction primeLambda =  (a) -> {
			for(int i = 2; i < a/2; i++) {
				if(a % i == 0) {
					System.out.println("Composite");
					return;
				}
			}
			System.out.println("Prime");
		};
		primeLambda.run(num);
	}
	
	public void isPalindrome(int num) {
		NumberFunction palindromeLambda = (a) -> {
			StringBuilder palCheck = new StringBuilder();
			palCheck.append(a);
			if(palCheck.toString().equals(palCheck.reverse().toString())){
				System.out.println("Palindrome");
			}else {
				System.out.println("Not Palindrome");
			}
		};
		palindromeLambda.run(num);
	}
}
