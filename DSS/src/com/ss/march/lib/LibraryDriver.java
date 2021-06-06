/**
 * 
 */
package com.ss.march.lib;

import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class LibraryDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryDriver menu = new LibraryDriver();
		menu.mainMenu();

	}

	public void mainMenu() {
		//Create admin, librarian and borrower objects
		Administrator admin = new Administrator();
		Librarian lib = new Librarian();
		Borrower bor = new Borrower();
		Scanner scan = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("Welcome to the SS Library Management System. Which category of a user are you");
				System.out.println("1) Librarian\n2) Administrator\n3) Borrower\n4) Quit");
				int input = Integer.parseInt(scan.nextLine());
				switch(input) {
					case 1:
						//Call Librarian 
						System.out.println("Moving to enter branch");
						lib.librarainMenuRunner();
						break;
					case 2:
						//Call admin
						System.out.println("Moving to main menu");
						admin.adminMenuRunner();
						break;
					case 3:
						//Call borrower
						bor.borrowerMenuRunner();
						break;
					case 4:
						//Quit
						System.out.println("Thank you for visting the library");
						return;
					default:
						//Repeat menu
						System.out.println("Enter one through four");
				}
			}
		}catch(NumberFormatException ex) {
//			ex.printStackTrace();
			System.out.println("You must enter a number");
		}
	}
}
