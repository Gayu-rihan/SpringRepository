package com.iprimed.ab.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.iprimed.ab.bean.Author;

//Author table 
public class AuthorDao implements IAuthorDao {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	int authorId;
	
//	create / insert table
	@Override
	public int authorDetails(Author author) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			String sqlIdentifier = "SELECT AUTHOR_SEQ.NEXTVAL FROM DUAL";
			pstmt = conn.prepareStatement(sqlIdentifier);
			synchronized (this) {
				rs = pstmt.executeQuery();
				if (rs.next())
					author.setAuthorId(rs.getInt(1));
			}
			DatabaseMetaData dbm = conn.getMetaData();
			// check if "authorloyee" table is there
			ResultSet tables = dbm.getTables(null, null, "AUTHOR", null);
			if (tables.next()) {
				pstmt = conn.prepareStatement("INSERT INTO AUTHOR VALUES(?,?,?,?,?)");
				pstmt.setInt(1, author.getAuthorId());
				pstmt.setString(2, author.getFirstName());
				pstmt.setString(3, author.getMiddleName());
				pstmt.setString(4, author.getLastName());
				pstmt.setLong(5, author.getPhoneNo());
				rs = pstmt.executeQuery();
			} else {
				// If Table does not exist
				stmt = conn.createStatement();
				String sql = "CREATE TABLE AUTHOR(AUTHOR_ID NUMBER(20) PRIMARY KEY, FIRST_NAME VARCHAR(25), MIDDLE_NAME VARCHAR(25), LAST_NAME VARCHAR(25),PHONE_NO NUMBER(15))";
				stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement("INSERT INTO AUTHOR VALUES(?,?,?,?,?)");
				pstmt.setInt(1, author.getAuthorId());
				pstmt.setString(2, author.getFirstName());
				pstmt.setString(3, author.getMiddleName());
				pstmt.setString(4, author.getLastName());
				pstmt.setLong(5, author.getPhoneNo());
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
		return author.getAuthorId();

	}

//	Read author 
	@Override
	public void display(Author author) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from author"); // customer==your table name
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

//	update author details
	@Override
	public void update(int authorId, long phoneNo) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Epm_12345");

			pstmt = conn
					.prepareStatement("update author set phone_no= " + phoneNo + " where author_id= " + authorId + " ");
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
