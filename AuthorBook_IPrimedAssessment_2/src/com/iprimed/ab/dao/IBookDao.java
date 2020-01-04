package com.iprimed.ab.dao;

import com.iprimed.ab.bean.Book;

public interface IBookDao {
//	Abstract methods
	public int bookDetails(Book book);

	public void display(Book book);

	public void update(int ISBN, double price);

}
