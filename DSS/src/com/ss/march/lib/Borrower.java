package com.ss.march.lib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrower {
	
	private int cardNumber;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Borrower b = new Borrower();
		b.borrowerMenuRunner();
//		b.checkOutBook();
		System.out.println("Cardnumber: " + b.cardNumber);
	}
	
	public boolean readCardnumber() throws ClassNotFoundException, SQLException {
		System.out.println("Please enter a card number");
		LibraryBorrowerDAO borrowerDAO = new LibraryBorrowerDAO();
		try {
			Scanner scan = new Scanner(System.in);
			int input = Integer.parseInt(scan.nextLine());
			if(borrowerDAO.checkCardno(input) == 1) {
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
	
	public int borrowMenuOne() throws ClassNotFoundException, SQLException {
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
	public int checkOutBook() throws ClassNotFoundException, SQLException {
		try {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO();
			LibraryBorrowerDAO borrowerDAO = new LibraryBorrowerDAO();
			LibraryBookLoanDAO bookLoanDAO = new LibraryBookLoanDAO();
			LibraryBookCopiesDAO bookCopiesDAO = new LibraryBookCopiesDAO();
			Scanner scan = new Scanner(System.in);
			int numBranches = 0;
			List<LibraryBranch> branches = branchDAO.getAll();
			for(LibraryBranch branch: branches) {
				numBranches++;
				System.out.println(numBranches + ") " + branch);
			}
			System.out.println((numBranches + 1) + ") " + "Quit to cancel operation");
			//Choose a branch
			int branchChoice = Integer.parseInt(scan.nextLine());
			if(branchChoice > 0 && branchChoice < numBranches+1) {
				System.out.println("Chosen branch is " + branches.get(branchChoice-1));
				int numBooks = 0;
				List<LibraryBookAuthorBranch> books = borrowerDAO.avaliableBooks(branches.get(branchChoice-1).getBranchId());
				for(LibraryBookAuthorBranch book: books) {
					numBooks++;
					System.out.println(numBooks + ") " + book.getTitle() + ", " + book.getAuthorName());
				}
				System.out.println((numBooks + 1) + ") " + "Quit to cancel operation");
				//Choose a book
				int bookChoice = Integer.parseInt(scan.nextLine());
				if(bookChoice > 0 && bookChoice < numBooks + 1) {
					System.out.println("Checked book is " + books.get(bookChoice-1));
					LibraryBookLoan checkedBook = new LibraryBookLoan(books.get(bookChoice-1).getBookId(),books.get(bookChoice-1).getBranchId(),cardNumber,null,null,null);
					bookLoanDAO.save(checkedBook);
					bookCopiesDAO.subtractCopy(books.get(bookChoice-1).getBookId(),books.get(bookChoice-1).getBranchId());
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
	
	public int returnBook() throws ClassNotFoundException, SQLException {
		try {
			LibraryBookLoanDAO bookLoanDAO = new LibraryBookLoanDAO();
			LibraryBorrowerDAO borrowerDAO = new LibraryBorrowerDAO();
			LibraryBookCopiesDAO bookCopiesDAO = new LibraryBookCopiesDAO();
			Scanner scan = new Scanner(System.in);
			int numBranches = 0;
			List<LibraryBranch> branches = borrowerDAO.checkedOutBookBranches(cardNumber);
			for(LibraryBranch branch: branches) {
				numBranches++;
				System.out.println(numBranches + ") " + branch);
			}
			System.out.println((numBranches + 1) + ") " + "Quit to cancel operation");
			//Choose a branch
			int branchChoice = Integer.parseInt(scan.nextLine());
			if(branchChoice > 0 && branchChoice < numBranches+1) {
				System.out.println("Chosen branch is " + branches.get(branchChoice-1));
				int numBooks = 0;
				List<LibraryBookAuthorBranch> books = borrowerDAO.checkedOutBookFor(cardNumber); 
				for(LibraryBookAuthorBranch book: books) {
					numBooks++;
					System.out.println(numBooks + ") " + book.getTitle() + ", " + book.getAuthorName());
				}
				System.out.println((numBooks + 1) + ") " + "Quit to cancel operation");
				//Choose a book
				int bookChoice = Integer.parseInt(scan.nextLine());
				if(bookChoice > 0 && bookChoice < numBooks + 1) {
					System.out.println("Returned book is " + books.get(bookChoice-1));
					//TODO Remove the book loan entry for that book at that branch
					LibraryBookLoan returnedBook = new LibraryBookLoan(books.get(bookChoice-1).getBookId(),books.get(bookChoice-1).getBranchId(),cardNumber , null, null, null);
					bookLoanDAO.returnBook(returnedBook);
					bookCopiesDAO.addCopy(books.get(bookChoice-1).getBookId(),books.get(bookChoice-1).getBranchId());
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
	
	public void borrowerMenuRunner() throws ClassNotFoundException, SQLException{
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
