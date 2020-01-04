package com.iprimed.ab.ui;

import java.util.Scanner;
import com.iprimed.ab.bean.Author;
import com.iprimed.ab.bean.Book;
import com.iprimed.ab.dao.AuthorDao;
import com.iprimed.ab.dao.BookDao;

/**
 * @author Gayathri
 *
 */
public class UserInterface {
	/**
	 * @param args
	 */
//	Implementation begins
	public void implementation() {
		Book book = new Book();
		Author author = new Author();
		AuthorDao authorDao = new AuthorDao();
		BookDao bookDao = new BookDao();
		Scanner input = new Scanner(System.in);
		
		String option = "NO";
		while (true) {

			System.out.println("Choices : " + "\n\t" + "1. Author registration" + "\n\t"
					+ "2. Book registration" + "\n\t"
					+ "3. Display author and book details" + "\n\t" 
					+ "4. Update author details" + "\n\t"
					+ "5. Update book details" + "\n" + "Enter Choice :");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
//				Author registration
				System.out.println("Enter below details");
				System.out.println("First Name : ");
				author.setFirstName(input.next());
				System.out.println("Middle Name : ");
				author.setMiddleName(input.next());
				System.out.println("Last Name : ");
				author.setLastName(input.next());
				System.out.println("Phone Number : ");
				author.setPhoneNo(input.nextLong());
				System.out.println("Author ID is : \t" + authorDao.authorDetails(author));
				break;
				
			case 2 :
//				Book registration
				System.out.println("Enter below details");
				System.out.println("Book Title : ");
				book.setTitle(input.next());
				System.out.println("Book Price : ");
				book.setPrice(input.nextDouble());
				System.out.println("Author Id : ");
				book.setAuthorId(input.nextInt());
				System.out.println("Book ISBN ID is : \t" + bookDao.bookDetails(book));
				break;
				
			case 3:
//				Displays author and book details - book title
				System.out.println("Author Details");
				authorDao.display(author);
				System.out.println("***************************************");
				System.out.println("Book Details");
				bookDao.display(book);
				break;
				
			case 4:
//				Update author details
				System.out.println("Enter author id");
				int id = input.nextInt();
				System.out.println("Enter updated mobile number");
				long phoneNo = input.nextLong();
				authorDao.update(id,phoneNo);
				break;
				
			case 5:
//				Update book details
				System.out.println("Enter book isbn");
				int isbn = input.nextInt();
				System.out.println("Enter book price");
				Double price = input.nextDouble();
				bookDao.update(isbn,price);
				break;
				
			default:
				System.out.println("Invalid choice");

			}

			System.out.println("Do you want to continue ? (YES / NO)");
			option = input.next();
//			Checking condition to exit
			if (option.equalsIgnoreCase("No")) {
				System.exit(0);
//				Close scanner object			
				input.close();
			}

		}

	}

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
//		Executable method
		ui.implementation();

	}

}
