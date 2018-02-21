package com.frank.lms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Book {
	private String title;
	private String subtitle;
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


	private static Calendar string2Calendar(String publishedDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		String[] strings = publishedDate.split("-");
		if (strings.length == 1) calendar.set(Integer.parseInt(strings[0]), 1, 1);
		if (strings.length == 2) calendar.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), 1);
		if (strings.length == 3) calendar.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer
				.parseInt(strings[2]));
		return calendar;
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
	}


	public Book(String title, String subtitle, String publishedDate, String description, int pageCount, String
			imageLinks, List<String> authors, String isbn10, String isbn13, String url) {
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
				", authors=" + authors +
				", isbn10='" + isbn10 + '\'' +
				", isbn13='" + isbn13 + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
