/**
 * 
 */
package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class MainMenu {
	
	public void mainMenu() throws ClassNotFoundException, SQLException {
		int input;
		TravelerMenu tm = new TravelerMenu();
		AdminMenu am = new AdminMenu();
		System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you");
		while(true) {
			System.out.println("1) Employee\n2) Administrator\n3) Traveler\n4) Quit");
			input = takeInputInt(4);
			switch(input) {
				case 1:
					System.out.println("Not Yet Implmented");
					break;
				case 2:
					am.AdminMenuRunner();
					break;
				case 3:
					tm.travelerMenuRunner();
					break;
				case 4:
					System.out.println("Thank you for using the Utopia Airlines Management System");
					return;
			}
		}
	}
	
	private int takeInputInt(int max) {
		if(max == 0  || max < 0) {
			return 0;
		}
		Scanner scan = new Scanner(System.in);
		int input = 0;
		while(true) {
			try {
				input = Integer.parseInt(scan.nextLine());
				if(input > 0 && input <= max) {
					return input;
				}else {
					System.out.println("Enter 1-" + max);
				}
			}catch(NumberFormatException e){
				System.out.println("Please Enter a Number");
			}
		}
	}
}
