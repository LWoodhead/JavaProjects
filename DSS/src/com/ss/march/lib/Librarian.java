/**
 * 
 */
package com.ss.march.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class Librarian {

	private String currentLibrary;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Librarian l = new Librarian();
//		l.libOneMenu();
//		l.libTwoMenu();
//		l.libMenuThree();
		l.librarainMenuRunner();
		System.out.println("Current lib: "+ l.currentLibrary);
	}

	public int libOneMenu() {
		System.out.println("1)	Enter Branch you manage\n2)	Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					//Move to branch menu
					System.out.println("Moving to enter branch");
					return 2;
				case 2:
					//Move back to main menu
					System.out.println("Moving to main menu");
					return 0;
				default:
					//Repeat menu
					System.out.println("Enter one or two");
					return -1;
			}
			
		}catch(NumberFormatException ex) {
//			ex.printStackTrace();
			System.out.println("You must enter a number");
		}
		return -1;
	}
	
	public int libTwoMenu() {
		int numBranches = 0;
		List<String> branches = new ArrayList<>();
		//Temp values
		branches.add("University Library, Boston");
		branches.add("State Library, New York");
		branches.add("Federal Library, Washington DC");
		//Get list of branches from database and covert to a list
		//TODO
		//Print
		for(String branch: branches) {
			numBranches++;
			System.out.println(numBranches + ") " + branch);
		}
		System.out.println((numBranches + 1) + ") " + "Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			if(input > 0 && input < numBranches+1) {
				//Need to pass correct branch back to menu function somehow *Done*
				//Move to next menu three
				currentLibrary = branches.get(input-1);
				System.out.println("Moving to manage branch " + branches.get(input-1));
				return 3;
			}else if(input == numBranches+1){
				//Move back to menu one
				System.out.println("Moving back to libMenuOne");
				return 1;
			}else{
				//Number was two large repeat prompt
				return -1;
			}
			
		}catch(NumberFormatException ex) {
//			ex.printStackTrace();
			System.out.println("You must enter a number");
		}
		return -1;
	}
	
	public int libMenuThree(){
		System.out.println("1) Update the details of the Library\n2) Add copies of Book to the Branch\n3) Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					//Update library details
					System.out.println("Updating branch");
					//TODO Take input and use it to update the database
					//Return to lib menu three
					return 3;
				case 2:
					//Add books
					//TODO Add copies of books to the DB
					System.out.println("Adding book");
					//Return to lib menu three
					return 3;
				case 3:
					//Move back to menu two
					System.out.println("Moving to menu two");
					return 2;
				default:
					//Repeat menu
					System.out.println("Enter one through three");
					return -1;
			}
			
		}catch(NumberFormatException ex) {
//			ex.printStackTrace();
			System.out.println("You must enter a number");
		}
		return -1;
	}
	public void librarainMenuRunner(){
		int currentMenu = 1;
		int nextMenu = -1;
		while(currentMenu != 0) {
			switch(currentMenu){
				case -1:
					//An error occurred call the same menu function again
					nextMenu = -1;
				case 0:
					//Break and the function ends moving us back to the main menu
					nextMenu = 0;
					break;
				case 1:
					//Call the first menu
					nextMenu = libOneMenu();
					break;
				case 2:
					//Call the second menu
					nextMenu = libTwoMenu();
					break;
				case 3:
					//Call the third menu
					nextMenu = libMenuThree();
					break;
			}
			if(nextMenu != -1) {
				currentMenu = nextMenu;
			}
		}
	}
}
