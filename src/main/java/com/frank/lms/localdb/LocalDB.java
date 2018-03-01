package com.frank.lms.localdb;

import com.frank.lms.Book;
import com.frank.lms.localdb.ColumnLabel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalDB {
	private final static String URL = "jdbc:mysql://localhost:3306/LibraryManagementSystem?useUnicode=true" +
			"&characterEncoding=utf8";
	private final static String USER = "root";
	private final static String PASSWORD = "402463";
	private final static String TABLE = "book";

	public List<Book> queryBook(ColumnLabel columnLabel, String value) {
		List<Book> books = new ArrayList<>();
		try (
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = conn.createStatement()
		) {
			String select = String.format("SELECT isbn10, isbn13, title, subtitle, authors, publishedDate, " +
					"description, publisher, imageLink, " +
					"pageCount FROM %s WHERE %s = '%s'", TABLE, columnLabel, value);
			if (statement.execute(select)) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Book book = new Book();
					book.setIsbn10(resultSet.getString("isbn10"));
					book.setIsbn13(resultSet.getString("isbn13"));
					book.setTitle(resultSet.getString("title"));
					book.setSubtitle(resultSet.getString("subtitle"));
					List<String> authors = Arrays.asList(resultSet.getString("authors").split("&"));
					book.setAuthors(authors);
					book.setPublisher(resultSet.getString("publisher"));
					book.setPublishedDate(resultSet.getString("publishedDate"));
					book.setDescription(resultSet.getString("description"));
					book.setPageCount(resultSet.getInt("pageCount"));
					book.setImageLinks(resultSet.getString("imageLink"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean addBook(Book book) {
		boolean confirm = false;
		try (
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement()
		) {
			String authors = String.join("&", book.getAuthors());
			String insert = String.format("INSERT INTO book (isbn10, isbn13, title, subtitle, authors, " +
							"publisher, " +
							"publishedDate, " +
							"description, " +
							"imageLink, " +
							" pageCount) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d')", book
							.getIsbn10(),
					book
							.getIsbn13(), book.getTitle(), book.getSubtitle(), authors, book.getPublisher(), book
							.getPublishedDate(), book
							.getDescription(), book.getImageLinks(), book.getPageCount());

			/*
			byte[] byteText = insert.getBytes(Charset.forName("UTF-8"));
			String query = new String(byteText, "UTF-8");
			*/
			if (statement.executeUpdate(insert) == 1) {
				confirm = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confirm;
	}
}
