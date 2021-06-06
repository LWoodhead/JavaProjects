package com.ss.march.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrower {
	
	private int cardNumber;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Borrower b = new Borrower();
		b.borrowerMenuRunner();
		System.out.println("Cardnumber: " + b.cardNumber);
	}
	
	public boolean readCardnumber() {
		System.out.println("Please enter a card number");
		try {
			Scanner scan = new Scanner(System.in);
			int input = Integer.parseInt(scan.nextLine());
			//TODO compare card number to database and return if it matches
			if(input == 123) {
				cardNumber = input;
				return true;
			}else {
				return false;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
		return false;
	}
	
	public int borrowMenuOne() {
		System.out.println("1) Check out a book\n2) Return a Book\n3) Quit to previous");
		Scanner scan = new Scanner(System.in);
		int ret;
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					ret = checkOutBook();
					if(ret == -1) {
						System.out.println("Failed to checkout book");
					}else {
						System.out.println("Successful checkout");
					}
					return 1;
				case 2:
					ret = returnBook();
					if(ret == -1) {
						System.out.println("Failed to return book");
					}else {
						System.out.println("Successful checkout");
					}
					return 1;
				case 3:
					return 0;
				default:
					System.out.println("Enter one through three");
					return -1;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
		return -1;
	}
	public int checkOutBook() {
		try {
			Scanner scan = new Scanner(System.in);
			int numBranches = 0;
			List<String> branches = new ArrayList<>();
			//Temp values
			branches.add("University Library, Boston");
			branches.add("State Library, New York");
			branches.add("Federal Library, Washington DC");
			//TODO retrieve branches from db and print them
			for(String branch: branches) {
				numBranches++;
				System.out.println(numBranches + ") " + branch);
			}
			System.out.println((numBranches + 1) + ") " + "Quit to cancel operation");
			//Choose a branch
			int branchChoice = Integer.parseInt(scan.nextLine());
			if(branchChoice > 0 && branchChoice < numBranches+1) {
				System.out.println("Chosen branch is " + branches.get(branchChoice-1));
				int numBooks = 0;
				List<String> books = new ArrayList<>();
				//TODO retrieve list of books from db and print them
				books.add("Lost Tribe by Sidney Sheldon");
				books.add("The Haunting by Stepehen King");
				books.add("Microtrends by Mark Penn");
				for(String book: books) {
					numBooks++;
					System.out.println(numBooks + ") " + book);
				}
				System.out.println((numBooks + 1) + ") " + "Quit to cancel operation");
				//Choose a book
				int bookChoice = Integer.parseInt(scan.nextLine());
				if(bookChoice > 0 && bookChoice < numBooks + 1) {
					System.out.println("Checked book is " + books.get(bookChoice-1));
					//TODO Then add entry into book_loans, date out should be today’s date, due date should be one week from today’s date.
					return 1;
				}else if(bookChoice == numBooks+1) {
					System.out.println("Canceling Operation");
					return -1;
				}else {
					System.out.println("Please enter a number corresponding to an existing book");
					return -1;
				}
				
			}else if(branchChoice == numBranches+1) {
				System.out.println("Canceling Operation");
				return -1;
			}else{
				System.out.println("Please enter a number corresponding to an existing branch");
				return -1;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
		return -1;
	}
	
	public int returnBook() {
		try {
			Scanner scan = new Scanner(System.in);
			int numBranches = 0;
			List<String> branches = new ArrayList<>();
			//Temp values
			branches.add("University Library, Boston");
			branches.add("State Library, New York");
			branches.add("Federal Library, Washington DC");
			//TODO retrieve branches that have a checked out book from the cardholder from db and print them
			for(String branch: branches) {
				numBranches++;
				System.out.println(numBranches + ") " + branch);
			}
			System.out.println((numBranches + 1) + ") " + "Quit to cancel operation");
			//Choose a branch
			int branchChoice = Integer.parseInt(scan.nextLine());
			if(branchChoice > 0 && branchChoice < numBranches+1) {
				System.out.println("Chosen branch is " + branches.get(branchChoice-1));
				int numBooks = 0;
				List<String> books = new ArrayList<>();
				//TODO retrieve list of books from db and print them if they are currently checked otu by the card holder
				books.add("Lost Tribe by Sidney Sheldon");
				books.add("The Haunting by Stepehen King");
				books.add("Microtrends by Mark Penn");
				for(String book: books) {
					numBooks++;
					System.out.println(numBooks + ") " + book);
				}
				System.out.println((numBooks + 1) + ") " + "Quit to cancel operation");
				//Choose a book
				int bookChoice = Integer.parseInt(scan.nextLine());
				if(bookChoice > 0 && bookChoice < numBooks + 1) {
					System.out.println("Returned book is " + books.get(bookChoice-1));
					//TODO Remove the book loan entry for that book at that branch
					return 1;
					
				}else if(bookChoice == numBooks+1) {
					System.out.println("Canceling Operation");
					return -1;
				}else {
					System.out.println("Please enter a number corresponding to an existing book");
					return -1;
				}
			}else if(branchChoice == numBranches+1){
				System.out.println("Canceling Operation");
				return -1;
			}else{
				System.out.println("Please enter a number corresponding to an existing branch");
				return -1;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
		return -1;
	}
	
	public void borrowerMenuRunner(){
		boolean validCardNumber = false;
		while(!validCardNumber) {
			validCardNumber = readCardnumber();
		}
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
					nextMenu = borrowMenuOne();
					break;
			}
			if(nextMenu != -1) {
				currentMenu = nextMenu;
			}
		}
	}
}
