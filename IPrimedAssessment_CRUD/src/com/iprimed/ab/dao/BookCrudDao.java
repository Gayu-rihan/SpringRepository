package com.iprimed.ab.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iprimed.author.bean.Book;

public class BookCrudDao implements IBookCrudDao {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	int authorId;
	
	@Override
	public int create(Book book) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			String sqlIdentifier = "SELECT BOOK_SEQ1.NEXTVAL FROM DUAL";
			pstmt = conn.prepareStatement(sqlIdentifier);
			synchronized( this) {
				rs = pstmt.executeQuery();
				if(rs.next())
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
				rs=pstmt.executeQuery();
			} else {
				// If Table does not exist
				stmt = conn.createStatement();
				String sql = "CREATE TABLE BOOK(ISBN NUMBER(20) PRIMARY KEY, TITLE VARCHAR(40), PRICE NUMBER(10), AUTHOR_ID NUMBER(20))";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement("INSERT INTO BOOK VALUES(?,?,?,?)");
				pstmt.setInt(1, book.getISBN());
				pstmt.setString(2, book.getTitle());
				pstmt.setDouble(3, book.getPrice());
				pstmt.setDouble(4, book.getAuthorId());
				pstmt.executeUpdate();
				
			}
		}catch (SQLException e) {
			System.out.println(e);
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book.getISBN();
	
	}
	
	public Book read(Book book) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("select * from book where title="+"'"+book.getTitle()+"'");
			while (r.next()) {
				book = new Book();
				book.setISBN(r.getInt(1));
				book.setTitle(r.getString(2));
				book.setPrice(r.getDouble(3));

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return book;

	}
	public Book update(Book book) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
			Statement s = conn.createStatement();
			int update=s.executeUpdate("update book set price="+book.getPrice()+" where isbn=" +"'"+book.getISBN()+"'");	
			ResultSet r=s.executeQuery("select * from book where isbn="+"'"+book.getISBN()+"'");
			while (r.next()) {
				book = new Book();
				book.setISBN(r.getInt(1));
				book.setTitle(r.getString(2));
				book.setPrice(r.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return book;
	}
	



		public int delete(Book book) {
			
			int delete=0;
			//boolean delete ;
			// TODO Auto-generated method stub
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
				Statement s=conn.createStatement();
				
				 delete=s.executeUpdate("delete from book where isbn="+"'"+book.getISBN()+"'");
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return delete;
		} 


}
