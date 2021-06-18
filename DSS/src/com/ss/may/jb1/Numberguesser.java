package com.ss.may.jb1;
import java.util.Scanner;
import java.util.Random;

public class Numberguesser {
	public static void main(String args[]) 
	{
		Random rand = new Random();
		int target_num = rand.nextInt(101);
		Scanner myObj = new Scanner(System.in);
		boolean notFound = true; 
		for(int i=0;i<5;i++) {
			int guessesLeft = 4 - i;
			System.out.println("Guess a number");
			String num = myObj.nextLine();
			System.out.println(num);
			int difference = target_num - Integer.parseInt(num);
			if(Math.abs(difference) <= 10){
				System.out.println("Close enough random number was " + target_num);
				notFound = false;
				break;
			}else{
				System.out.println("Incorrect, you have " + guessesLeft + " chances remaining");
			}
		}
		if(notFound) {
			System.out.println("Better luck next time, the random number was " + target_num);
		}
		myObj = null;
	}
}

