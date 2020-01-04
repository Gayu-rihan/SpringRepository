package com.iprimed.ab.bean;

public class Book {

	private int iSBN;
	private String title;
	private double price;
	private int authorId;

	public Book() {
	}

	/**
	 * @param iSBN
	 * @param title
	 * @param price
	 */
	public Book(int iSBN, String title, double price) {
		super();
		this.iSBN = iSBN;
		this.title = title;
		this.price = price;
	}

	/**
	 * @return the iSBN
	 */
	public int getISBN() {
		return iSBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(int iSBN) {
		this.iSBN = iSBN;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

}
