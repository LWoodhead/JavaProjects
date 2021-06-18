/**
 * 
 */
package com.ss.march.lib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class Librarian {

	private LibraryBranch currentLibrary;
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Librarian l = new Librarian();
//		l.libOneMenu();
//		l.libTwoMenu();
//		l.libMenuThree();
		l.librarainMenuRunner();
//		l.addBook();
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
	
	public int libTwoMenu() throws ClassNotFoundException, SQLException {
		int numBranches = 0;
		LibraryBranchDAO branchGet = new LibraryBranchDAO();
		//Get list of branches from database and covert to a list
		List<LibraryBranch> branches = branchGet.getAll();
		//Print
		for(LibraryBranch branch: branches) {
			numBranches++;
			System.out.println(numBranches + ") " + branch.toString());
		}
		System.out.println((numBranches + 1) + ") " + "Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			if(input > 0 && input < numBranches+1) {
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
	
	public int libMenuThree() throws ClassNotFoundException, SQLException{
		System.out.println("1) Update the details of the Library\n2) Add copies of Book to the Branch\n3) Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					//Update library details
					if(updateLibraryBranch() == -1) {
						System.out.println("Update Failed");
					}else {
						System.out.println("Update Successful");
					}
					return 3;
				case 2:
					//Add books
					if(addBook() == -1) {
						System.out.println("Book copies not added");
					}else {
						System.out.println("Book copies added");
					}
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
	public void librarainMenuRunner() throws ClassNotFoundException, SQLException{
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
	public int updateLibraryBranch() throws ClassNotFoundException, SQLException {
		LibraryBranchDAO branchDAO = new LibraryBranchDAO();
		Scanner scan = new Scanner(System.in);
		System.out.println("You have chosen to update the Branch with Branch Id: "+ currentLibrary.getBranchId() +" and Branch Name: "+currentLibrary.getBranchName()+
				".\nEnter ‘quit’ at any prompt to cancel operation.\nPlease enter new branch name or enter N/A for no change:");
		String updateParams[] = new String[2];
		updateParams[0] = scan.nextLine();
		if("quit".equals(updateParams[0])) {
			return -1;
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		updateParams[1] = scan.nextLine();
		if("quit".equals(updateParams[1])) {
			return -1;
		}
		if("N/A".equals(updateParams[1])) {
			updateParams[1] = currentLibrary.getBranchAddress();
		}
		if("N/A".equals(updateParams[0])) {
			updateParams[0] = currentLibrary.getBranchName();
		}
		currentLibrary.setBranchAddress(updateParams[1]);
		currentLibrary.setBranchName(updateParams[0]);
		branchDAO.update(currentLibrary, updateParams);
		//Return to lib menu three
		return 3;
	}
	public int addBook() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		LibraryBookAuthorDAO bookAuthorDAO = new LibraryBookAuthorDAO();
		LibraryBookCopiesDAO bookCopiesDOA = new LibraryBookCopiesDAO();
		List<LibraryBookAuthor> books = bookAuthorDAO.getAll();
		int numBooks = 0;
		for(LibraryBookAuthor book: books) {
			numBooks++;
			System.out.println((numBooks)+") "+book.getTitle() + ", by " + book.getAuthor());
		}
		System.out.println((numBooks + 1) + ") " + "Quit to previous");
		try {
			int input = Integer.parseInt(scan.nextLine());
			if(input <= 0 || input > numBooks+1) {
				return -1;
			}else {
				int numCopies = bookCopiesDOA.returnNumCopies(books.get(input-1).getBookId(), currentLibrary.getBranchId());
				if(numCopies == -1) {
					System.out.println("Existing number of copies: " + 0 +"\nEnter new number of copies:");
					int addedBooks = Integer.parseInt(scan.nextLine());
					LibraryBookCopies BookCopies = new LibraryBookCopies(books.get(input-1).getBookId(),currentLibrary.getBranchId(),addedBooks);
					bookCopiesDOA.save(BookCopies);
					return 1;
				}else {
					System.out.println("Existing number of copies: " + numCopies +"\nEnter new number of copies:");
					String copies = scan.nextLine();
					LibraryBookCopies BookCopies = new LibraryBookCopies(books.get(input-1).getBookId(),currentLibrary.getBranchId(),0);
					String[] params = new String[1];
					params[0] = copies;
					bookCopiesDOA.update(BookCopies, params);
					return 1;
				}
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
		return -1;
	}
}
