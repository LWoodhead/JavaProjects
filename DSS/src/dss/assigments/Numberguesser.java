package dss.assigments;
import java.util.Scanner;
import java.util.Random;

public class Numberguesser {
	public static void main(String args[]) 
	{
		Random rand = new Random();
		int target_num = rand.nextInt(101);
		Scanner myObj = new Scanner(System.in);
		int guesses_left = 5;
		for(int i=0;i<5;i++) {
			guesses_left = 4 - i;
			System.out.println("Guess a number");
			String num = myObj.nextLine();
			System.out.println(num);
			int difference = target_num - Integer.parseInt(num);
			if(Math.abs(difference) <= 10){
				System.out.println("Close enough random number was " + target_num);
				break;
			}else{
				System.out.println("Incorrect, you have " + guesses_left + " chances remaining");
			}
		}
		if(guesses_left == 0) {
			System.out.println("Better luck next time, the random number was " + target_num);
		}
	}
}

