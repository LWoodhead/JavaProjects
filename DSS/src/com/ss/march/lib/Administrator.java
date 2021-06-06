/**
 * 
 */
package com.ss.march.lib;

import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class Administrator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Administrator a = new Administrator();
		a.adminMenuRunner();
	}

	public int adminMenuOne(){
		System.out.println("1) Add/Update/Delete/Read Book and Author\n2) Add/Update/Delete/Read Genres\n3) Add/Update/Delete/Read Publishers\n4) Add/Update/Delete/Read Library Branches\n5) Add/Update/Delete/Read Borrowers\n6) Over-ride Due Date for a Book Loan\n7) Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					//TODO implement DOA and admin function call to complete
					System.out.println("DOA for Book and Author");
					return 1;
				case 2:
					System.out.println("DOA for Genres");
					return 1;
				case 3:
					System.out.println("DOA for Publishers");
					return 1;
				case 4:
					System.out.println("DOA for Library Branches");
					return 1;
				case 5:
					System.out.println("DOA for Borrowers");
					return 1;
				case 6:
					System.out.println("DOA for due date override");
					return 1;
				case 7:
					System.out.println("Returning to main menu");
					return 0;
				default:
					//Error case repeat menu
					System.out.println("Enter one through six");
					return -1;
			}
			
		}catch(NumberFormatException ex) {
//			ex.printStackTrace();
			System.out.println("You must enter a number");
		}
		return -1;
	}
	
	public void adminMenuRunner() {
		int currentMenu = 1;
		int nextMenu = -1;
		while(currentMenu != 0) {
			switch(currentMenu){
				case -1:
					nextMenu = currentMenu;
					break;
				case 0:
					nextMenu = -1;
					break;
				case 1:
					nextMenu = adminMenuOne();
					break;
			}
			if(nextMenu != -1) {
				currentMenu = nextMenu;
			}
		}
	}
	
}
