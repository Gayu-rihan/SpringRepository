package com.iprimed.ab.dao;

import com.iprimed.ab.bean.Author;

public interface IAuthorDao {
//	Abstract methods
	public int authorDetails(Author author);

	public void display(Author author);

	public void update(int authorId, long phoneNo);

}
