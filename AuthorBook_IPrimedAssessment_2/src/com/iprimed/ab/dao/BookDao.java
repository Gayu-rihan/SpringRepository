package com.iprimed.ab.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.iprimed.ab.bean.Book;

// Book table 
public class BookDao implements IBookDao {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	int authorId;

//	create/ insert table
	@Override
	public int bookDetails(Book book) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			String sqlIdentifier = "SELECT BOOK_SEQ.NEXTVAL FROM DUAL";
			pstmt = conn.prepareStatement(sqlIdentifier);
			synchronized (this) {
				rs = pstmt.executeQuery();
				if (rs.next())
					book.setISBN(rs.getInt(1));
			}
			DatabaseMetaData dbm = conn.getMetaData();
			// check if "BOOK" table is there
			ResultSet tables = dbm.getTables(null, null, "BOOK", null);
			if (tables.next()) {
				pstmt = conn.prepareStatement("INSERT INTO BOOK VALUES(?,?,?,?)");
				pstmt.setInt(1, book.getISBN());
				pstmt.setString(2, book.getTitle());
				pstmt.setDouble(3, book.getPrice());
				pstmt.setDouble(4, book.getAuthorId());
				rs = pstmt.executeQuery();
			} else {
				// If Table does not exist
				stmt = conn.createStatement();
				String sql = "CREATE TABLE BOOK(ISBN NUMBER(20) PRIMARY KEY, TITLE VARCHAR(40), PRICE NUMBER(10), AUTHOR_ID NUMBER(20),CONSTRAINT FK FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(AUTHOR_ID))";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement("INSERT INTO BOOK VALUES(?,?,?,?)");
				pstmt.setInt(1, book.getISBN());
				pstmt.setString(2, book.getTitle());
				pstmt.setDouble(3, book.getPrice());
				pstmt.setDouble(4, book.getAuthorId());
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book.getISBN();

	}

//	Read book
	@Override
	public void display(Book book) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from book"); // customer==your table name
			while (rs.next()) {
				System.out
						.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

//	Update book details
	@Override
	public void update(int ISBN, double price) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			pstmt = conn.prepareStatement("update book set price= " + price + " where isbn = " + ISBN + " ");
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
