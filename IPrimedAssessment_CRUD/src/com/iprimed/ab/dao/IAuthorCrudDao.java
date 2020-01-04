package com.iprimed.ab.dao;

import com.iprimed.author.bean.Author;

public interface IAuthorCrudDao {
	
	public int create(Author author);
	public Author read(int id);
	public Author update(int authorId, long phoneNo);
	public int delete(String name);

}
