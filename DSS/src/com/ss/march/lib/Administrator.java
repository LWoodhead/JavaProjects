/**
 * 
 */
package com.ss.march.lib;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class Administrator {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Administrator a = new Administrator();
		a.adminMenuRunner();
	}

	public int adminMenuOne() throws ClassNotFoundException, SQLException{
		System.out.println("1) Add/Update/Delete/Read Book and Author\n2) Add/Update/Delete/Read Genres\n3) Add/Update/Delete/Read Publishers\n4) Add/Update/Delete/Read Library Branches\n5) Add/Update/Delete/Read Borrowers\n6) Over-ride Due Date for a Book Loan\n7) Quit to previous");
		Scanner scan = new Scanner(System.in);
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					//TODO implement DOA and admin function call to complete
					authorBook();
					return 1;
				case 2:
					genres();
					return 1;
				case 3:
					publishers();
					return 1;
				case 4:
					branches();
					return 1;
				case 5:
					borrow();
					return 1;
				case 6:
					int cardNo,branchId,bookId;
					String[] params = new String[3];
					LibraryBookLoanDAO bookLoan = new LibraryBookLoanDAO();
					System.out.println("Please specify the cardNo");
					cardNo = Integer.parseInt(scan.nextLine());
					System.out.println("Please specify the branchId");
					branchId = Integer.parseInt(scan.nextLine());
					System.out.println("Please specify the bookId");
					bookId = Integer.parseInt(scan.nextLine());
					LibraryBookLoan update = new LibraryBookLoan(bookId, branchId, cardNo, null, null, null);
					bookLoan.update(update, params);
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
	
	public void adminMenuRunner() throws ClassNotFoundException, SQLException {
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
	public void authorBook() throws ClassNotFoundException, SQLException{
		Scanner scan = new Scanner(System.in);
		LibraryAuthorDAO authorDAO = new LibraryAuthorDAO();
		LibraryBookDAO bookDAO = new LibraryBookDAO();
		
		System.out.println("1) Add a Book or Author\n2) Update\n3) Delete\n4)Read Books and Authors\n5) Add to Author_Book");
		try {
			int input = Integer.parseInt(scan.nextLine());
			String title,author,pubId;
			int authId,bookId;
			String[] params = new String[3];
			switch(input) {
				case 1:
					System.out.println("Enter Book Title to Add or N/A");
					title = scan.nextLine();
					System.out.println("Enter Publisher ID to Add");
					pubId = scan.nextLine();
					System.out.println("Enter Author to Add or N/A");
					author = scan.nextLine();
					System.out.println("Enter Author to Add");
					author = scan.nextLine();
					if(!"N/A".equals(title)) {
						LibraryBook libBook = new LibraryBook(0,title,Integer.parseInt(pubId));
						bookDAO.save(libBook);
					}
					if(!"N/A".equals(author)) {
						LibraryAuthor libAuth = new LibraryAuthor(0, author);
						authorDAO.save(libAuth);
					}
					break;
				case 2:
					System.out.println("Enter Book Title to Update or N/A");
					title = scan.nextLine();
					System.out.println("Enter Publisher ID to Update or N/A");
					pubId = scan.nextLine();
					System.out.println("Enter Author to Update or N/A");
					author = scan.nextLine();
					if(!"N/A".equals(author)) {
						LibraryAuthor libAuth = new LibraryAuthor(0, author);
						params[0] = author;
						authorDAO.update(libAuth,params);
					}
					if(!"N/A".equals(title) && !"N/A".equals(pubId)) {
						LibraryBook libBook = new LibraryBook(0,title,Integer.parseInt(pubId));
						params[0] = pubId; 
						bookDAO.update(libBook,params);
					}
					
					break;
				case 3:
					//TODO let them delete a book or author
					System.out.println("Enter bookId to delete or N/A");
					pubId = scan.nextLine();
					if(!"N/A".equals(pubId)) {
						bookId = Integer.parseInt(pubId);
						LibraryBook libBook = new LibraryBook(bookId,"",0);
						bookDAO.delete(libBook);
					}
					System.out.println("Enter authorId to delete or N/A");
					pubId = scan.nextLine();
					if(!"N/A".equals(pubId)) {
						authId = Integer.parseInt(pubId);
						LibraryAuthor libAuth = new LibraryAuthor(authId, "");
						authorDAO.delete(libAuth);
					}
					break;
				case 4:
					bookDAO.printIdGenreTitleAuthor();
					break;
				case 5:
					System.out.println("Enter Book ID");
					bookId = Integer.parseInt(scan.nextLine());
					System.out.println("Enter Author ID");
					authId = Integer.parseInt(scan.nextLine());
					bookDAO.linkTables(bookId,authId);
				default:
					System.out.println("Enter one through five");
					break;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
	}
	
	public void genres() throws ClassNotFoundException, SQLException {
		LibraryGenresDAO genreDAO = new LibraryGenresDAO(); 
		Scanner scan = new Scanner(System.in);
		String genre,temp;
		int genreId,bookId;
		String[] params = new String[3];
		System.out.println("1) Add\n2) Update\n3) Delete\n4) Read Genres\n5) Link Genre and Book");
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("Enter New Genre to Add");
					genre = scan.nextLine();
					LibraryGenres add = new LibraryGenres(genre,0);
					genreDAO.save(add);
					break;
				case 2:
					System.out.println("Enter New Genre Name");
					genre = scan.nextLine();
					System.out.println("Enter Target GenreId");
					genreId = Integer.parseInt(scan.nextLine());
					params[0] = genre;
					LibraryGenres update = new LibraryGenres("",genreId);
					genreDAO.update(update, params);
					break;
				case 3:
					System.out.println("Enter Target GenreId to Delete");
					genreId = Integer.parseInt(scan.nextLine());
					LibraryGenres delete = new LibraryGenres("",genreId);
					genreDAO.delete(delete);
					break;
				case 4:
					List<LibraryGenres> printList = genreDAO.getAll();
					for(LibraryGenres ge: printList) {
						System.out.println(ge.toString());
					}
					break;
				case 5:
					System.out.println("Enter Genre ID");
					genreId = Integer.parseInt(scan.nextLine());
					System.out.println("Enter Book ID");
					bookId = Integer.parseInt(scan.nextLine());
					genreDAO.linkTables(bookId,genreId);
				default:
					System.out.println("Enter one through five");
					break;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
	}
	
	public void publishers() throws ClassNotFoundException, SQLException {
		LibraryPublisherDAO publisherDAO = new LibraryPublisherDAO(); 
		Scanner scan = new Scanner(System.in);
		String name,phone,address;
		int pubId;
		String[] params = new String[3];
		System.out.println("1) Add\n2) Update\n3) Delete\n4) Read Publishers");
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("Enter New Publisher Name to Add");
					name = scan.nextLine();
					System.out.println("Enter New Publisher Address to Add");
					address = scan.nextLine();
					System.out.println("Enter New Publisher Phone Number to Add");
					phone = scan.nextLine();
					LibraryPublisher add = new LibraryPublisher(0,name,address,phone);
					publisherDAO.save(add);
					break;
				case 2:
					System.out.println("Enter New Publisher Name to Update");
					params[0] = scan.nextLine();
					System.out.println("Enter New Publisher Address to Update");
					params[1] = scan.nextLine();
					System.out.println("Enter New Publisher Phone Number to Update");
					params[2] = scan.nextLine();
					LibraryPublisher update = new LibraryPublisher(0,"","","");
					publisherDAO.update(update, params);
					break;
				case 3:
					System.out.println("Enter Target publisherId to Delete");
					pubId = Integer.parseInt(scan.nextLine());
					LibraryPublisher delete = new LibraryPublisher(pubId,"","","");
					publisherDAO.delete(delete);
					break;
				case 4:
					List<LibraryPublisher> printList = publisherDAO.getAll();
					for(LibraryPublisher pu: printList) {
						System.out.println(pu.toString());
					}
					break;
				default:
					System.out.println("Enter one through four");
					break;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
	}
	
	public void branches() throws ClassNotFoundException, SQLException {
		LibraryBranchDAO branchDAO = new LibraryBranchDAO(); 
		Scanner scan = new Scanner(System.in);
		String name,address;
		int branchId;
		String[] params = new String[2];
		System.out.println("1) Add\n2) Update\n3) Delete\n4) Read Branches");
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("Enter New Branch Name to Add");
					name = scan.nextLine();
					System.out.println("Enter New Branch Address to Add");
					address = scan.nextLine();
					LibraryBranch add = new LibraryBranch(0,name,address);
					branchDAO.save(add);
					break;
				case 2:
					System.out.println("Enter New Branch Name to Update");
					params[0] = scan.nextLine();
					System.out.println("Enter New Branch Address to Update");
					params[1] = scan.nextLine();
					LibraryBranch update = new LibraryBranch(0,"","");
					branchDAO.update(update, params);
					break;
				case 3:
					System.out.println("Enter Target branchId to Delete");
					branchId = Integer.parseInt(scan.nextLine());
					LibraryBranch delete = new LibraryBranch(branchId,"","");
					branchDAO.delete(delete);
					break;
				case 4:
					List<LibraryBranch> printList = branchDAO.getAll();
					for(LibraryBranch lb: printList) {
						System.out.println(lb.toString());
					}
					break;
				default:
					System.out.println("Enter one through four");
					break;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
	}
	
	public void borrow() throws ClassNotFoundException, SQLException {
		LibraryBorrowerDAO borrowerDAO = new LibraryBorrowerDAO(); 
		Scanner scan = new Scanner(System.in);
		String name,address,phone;
		int cardNo;
		String[] params = new String[3];
		System.out.println("1) Add\n2) Update\n3) Delete\n4) Read Borrowers");
		try {
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("Enter New Borrower Name to Add");
					name = scan.nextLine();
					System.out.println("Enter New Borrower Address to Add");
					address = scan.nextLine();
					System.out.println("Enter New Borrower Phone to Add");
					phone = scan.nextLine();
					LibraryBorrower add = new LibraryBorrower(0,name,address,phone);
					borrowerDAO.save(add);
					break;
				case 2:
					System.out.println("Enter New Borrower Name to Update");
					params[0] = scan.nextLine();
					System.out.println("Enter New Borrower Address to Update");
					params[1] = scan.nextLine();
					System.out.println("Enter New Borrower Phone to Update");
					params[2] = scan.nextLine();
					LibraryBorrower update = new LibraryBorrower(0,"","","");
					borrowerDAO.update(update, params);
					break;
				case 3:
					System.out.println("Enter Target cardNo to Delete");
					cardNo = Integer.parseInt(scan.nextLine());
					LibraryBorrower delete = new LibraryBorrower(cardNo,"","","");
					borrowerDAO.delete(delete);
					break;
				case 4:
					List<LibraryBorrower> printList = borrowerDAO.getAll();
					for(LibraryBorrower lb: printList) {
						System.out.println(lb.toString());
					}
					break;
				default:
					System.out.println("Enter one through four");
					break;
			}
		}catch(NumberFormatException ex) {
			System.out.println("You must enter a number");
		}
	}
}
