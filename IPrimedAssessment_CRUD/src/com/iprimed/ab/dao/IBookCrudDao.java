package com.iprimed.ab.dao;

import com.iprimed.author.bean.Book;

public interface IBookCrudDao {

	public int create(Book book);
	public Book read(Book book);
	public Book update(Book book);
	public int delete(Book book);
}
