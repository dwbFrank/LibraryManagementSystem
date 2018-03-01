package com.frank.lms;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private String title;
	private String subtitle;
	private String publisher;
	private String publishedDate;
	private String description;
	private int pageCount;
	private String imageLinks;
	private List<String> authors;
	private String isbn10;
	private String isbn13;
	private String url;


	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public int getPageCount() {
		return pageCount;
	}

	public String getImageLinks() {
		return imageLinks;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public String getUrl() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setImageLinks(String imageLinks) {
		this.imageLinks = imageLinks;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public Book() {
		title = "見付けません";
		subtitle = "見付けません";
		publishedDate = "見付けません";
		description = "見付けません";
		pageCount = 0;
		imageLinks = "見付けません";
		authors = new ArrayList<>();
		authors.add("見付けません");
		isbn10 = "見付けません";
		isbn13 = "見付けません";
		publisher = "見付けません";
	}


	public Book(String title, String subtitle, String publishedDate, String description, int pageCount, String
			imageLinks, String publisher, List<String> authors, String isbn10, String isbn13, String url) {
		this.title = title;
		this.subtitle = subtitle;
		this.publishedDate = publishedDate;
		this.description = description;
		this.pageCount = pageCount;
		this.imageLinks = imageLinks;
		this.authors = authors;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.url = url;
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				", subtitle='" + subtitle + '\'' +
				", publishedDate=" + publishedDate +
				", description='" + description + '\'' +
				", pageCount=" + pageCount +
				", imageLinks='" + imageLinks + '\'' +
				", publisher=" + publisher + '\'' +
				", authors=" + authors +
				", isbn10='" + isbn10 + '\'' +
				", isbn13='" + isbn13 + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
