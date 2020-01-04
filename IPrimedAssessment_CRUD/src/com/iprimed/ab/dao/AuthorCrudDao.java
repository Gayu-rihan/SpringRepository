package com.iprimed.ab.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iprimed.author.bean.Author;
import com.iprimed.author.bean.Book;


public class AuthorCrudDao implements IAuthorCrudDao{
	Author author = new Author();
	Book book = new Book();
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	int authorId;
	long phoneNo;

	@Override
	public int create(Author author) {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			String sqlIdentifier = "SELECT AUTHOR_SEQ1.NEXTVAL FROM DUAL";
			pstmt = conn.prepareStatement(sqlIdentifier);
			synchronized( this) {
				rs = pstmt.executeQuery();
				if(rs.next()) {
					author.setAuthorId(rs.getInt(1));
					book.setAuthorId(rs.getInt(1));
				}
			}
			DatabaseMetaData dbm = conn.getMetaData();
			// check if "Author1" table is there
			ResultSet tables = dbm.getTables(null, null, "AUTHOR1", null);
			if (tables.next()) {
				pstmt = conn.prepareStatement("INSERT INTO AUTHOR1 VALUES(?,?,?,?,?)");
				pstmt.setInt(1, author.getAuthorId());
				pstmt.setString(2, author.getFirstName());
				pstmt.setString(3, author.getMiddleName());
				pstmt.setString(4, author.getLastName());
				pstmt.setLong(5, author.getPhoneNo());
				rs=pstmt.executeQuery();
			} else {
				// If Table does not exist
				stmt = conn.createStatement();
				String sql = "CREATE TABLE AUTHOR1(AUTHOR_ID NUMBER(20) PRIMARY KEY, FIRST_NAME VARCHAR(25), MIDDLE_NAME VARCHAR(25), LAST_NAME VARCHAR(25),PHONE_NO NUMBER(15))";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement("INSERT INTO AUTHOR1 VALUES(?,?,?,?,?)");
				pstmt.setInt(1, author.getAuthorId());
				pstmt.setString(2, author.getFirstName());
				pstmt.setString(3, author.getMiddleName());
				pstmt.setString(4, author.getLastName());
				pstmt.setLong(5, author.getPhoneNo());
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
		return author.getAuthorId();
	
	}

public Author read(int authorId) {
	try {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
		pstmt = conn.prepareStatement("select * from author1 where author_id= ?"); 
		pstmt.setInt(1, authorId);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2));//table values
		}
	} catch (Exception e) {
		e.printStackTrace();

	}

	return author;

}
public Author update(int authorId, long phoneNo) {
	try {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
		
		pstmt = conn.prepareStatement("update author1 set phone_no= "+ phoneNo + " where author_id= "+ authorId +" "); 
		rs = pstmt.executeQuery();
		
	
	} catch (Exception e) {
		e.printStackTrace();

	}
	return author;
}




	public int delete(String name) {
		String stu;
		int delete=0;
		//boolean delete ;
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
			Statement s=conn.createStatement();
			
			 delete=s.executeUpdate("delete from author1 where first_name="+"'"+name+"'");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return delete;
	}
}

