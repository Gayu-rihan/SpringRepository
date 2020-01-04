package com.iprimed.ab.ui;

import java.util.Scanner;
import com.iprimed.ab.dao.AuthorCrudDao;
import com.iprimed.ab.dao.BookCrudDao;
import com.iprimed.author.bean.Author;
import com.iprimed.author.bean.Book;

public class UserInterface {

	Scanner input = new Scanner(System.in);
	BookCrudDao bookDao = new BookCrudDao();
	AuthorCrudDao authorDao = new AuthorCrudDao();
	Author author = new Author();
	Book book = new Book();
	int choice;
	int flag = 0;
	String status = "No";

	public void authorDetails() {

		while(true) {
			
			System.out.println("Enter Your Option");
			System.out.println("1.Create/Insert");
			System.out.println("2.Read");
			System.out.println("3.Update");
			System.out.println("4.Delete");

			choice = input.nextInt();

			switch(choice) {
			case 1 : 
				System.out.println("enter the author first name");
				author.setFirstName(input.next());
				System.out.println("enter the middle name");
				author.setMiddleName(input.next());
				System.out.println("enter the lastname");
				author.setLastName(input.next());
				System.out.println("enter the phoneNo");
				author.setPhoneNo(input.nextLong());
				authorDao.create(author);
				System.out.println("Author table created" + "\n" + " Author Id is :\t " + author.getAuthorId());

				break;
			case 2 : 
				System.out.println("enter the author id");
				int id=input.nextInt();
				authorDao.read(id);
				System.out.println("Author table details are displayed");

				break;
			case 3 : 
				System.out.println("enter the author id");
				id = input.nextInt();
				System.out.println("Enter your updated Phone number");
				long phoneNo = input.nextLong();
				authorDao.update(id, phoneNo);
				System.out.println(author.getAuthorId() + "author details updated");

				break;
			case 4 : 
				System.out.println("enter the author first name");
				String name = input.next();
				flag = authorDao.delete(name);
				if (flag == 1) {
					System.out.println("Successfully Deleted");
				} else {
					System.out.println("There is no record in" + " " + author.getFirstName() + " " + author);
				}
				break;
			default : System.out.println("Invalid choice");
			
			}

			
			System.out.println("Do you want to continue ? (YES / NO)");
			status = input.next();
//			Checking condition to exit
			if (status.equalsIgnoreCase("No")) {
				System.exit(0);
//				Close scanner object			
				input.close();
			}
		}
		
	}

	public void bookDetails() {
		
		
		while(true) {
			System.out.println("Enter Your Option");

			System.out.println("1.Create/Insert");
			System.out.println("2.Read");
			System.out.println("3.Update");
			System.out.println("4.Delete");

			choice = input.nextInt();
			
			switch(choice) {
			case 1 : 
				System.out.println("enter the Book Tiltle ");
				book.setTitle(input.next());
				System.out.println("enter the price");
				book.setPrice(input.nextDouble());
				bookDao.create(book);
				System.out.println("Book table created" + "\n" + " Book Id is :\t " + book.getISBN());


				break;
			case 2 : 
				System.out.println("enter the title");
				book.setTitle(input.next());

				bookDao.read(book);
				System.out.println("Book details are displayed");

				break;
			case 3 : 
				System.out.println("enter the isbn");
				book.setISBN(input.nextInt());
				System.out.println("enter your updated price");
				book.setPrice(input.nextDouble());
				bookDao.update(book);
				System.out.println("Book details are updated");
				break;
			case 4 : 
				System.out.println("enter the ISBN");
				book.setISBN(input.nextInt());
				flag = bookDao.delete(book);
				if (flag == 1) {
					System.out.println("Successfully Deleted");
				} else {
					System.out.println("There is no record in" + " " + book.getISBN() + " " + "NUMBER");
				}
				break;
			default : System.out.println("Invalid choice");
			}


			System.out.println("Do you want to continue ? (YES / NO)");
			status = input.next();
//			Checking condition to exit
			if (status.equalsIgnoreCase("No")) {
				System.exit(0);
//				Close scanner object			
				input.close();
			}
		}
			
	}
		

	public void implementation() {
		UserInterface ui = new UserInterface();

		while(true) {
			
			System.out.println("Enter Your Option");
			System.out.println("1.CRUD operation in Author");
			System.out.println("2.CRUD operations in Book");
			int choice;
			choice = input.nextInt();

			switch (choice) {
			case 1:
				ui.authorDetails();
				break;
			case 2:
				ui.bookDetails();
				break;
			default:
				System.out.println("Invalid choice");

			}

			System.out.println("Do you want to continue ? (YES / NO)");
			status = input.next();
//			Checking condition to exit
			if (status.equalsIgnoreCase("No")) {
				System.exit(0);
//				Close scanner object			
				input.close();
			}
		}
	}

	

	public static void main(String args[]) {
		UserInterface ui = new UserInterface();
		ui.implementation();
	}

}
