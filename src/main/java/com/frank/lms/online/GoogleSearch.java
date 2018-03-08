package com.frank.lms.online;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.lms.Book;
import com.frank.lms.localdb.DatabaseColumnLabel;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearch {

	private static final String HOST = "www.googleapis.com";
	private static final String PATH = "/books/v1/volumes";
	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final String SCHEME = "https";
	private URI uri;

	public GoogleSearch(GoogleSearchTerm terms, String search) {
		List<NameValuePair> parameter = new ArrayList<>();
		parameter.add(new BasicNameValuePair("q", terms.getAbbreviation() + ":" + search));
		parameter.add(new BasicNameValuePair("fields", "totalItems,items(volumeInfo/title,volumeInfo/subtitle," +
				"volumeInfo/authors," +
				"volumeInfo/publishedDate,volumeInfo/description,volumeInfo/industryIdentifiers/*," +
				"volumeInfo/pageCount,volumeInfo/imageLinks/thumbnail,volumeInfo/publisher)"));
		parameter.add(new BasicNameValuePair("maxResults", "20"));
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setCharset(CHARSET);
		uriBuilder.setScheme(SCHEME).setHost(HOST).setPath(PATH).setParameters(parameter);
		try {
			this.uri = uriBuilder.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	private List<Book> parseBook(GoogleBooks googleBooks) {
		List<Book> bookList = new ArrayList<>();
		for (GoogleBooks.ItemsBean itemsBean : googleBooks.getItems()
				) {
			Book book = new Book();
			GoogleBooks.ItemsBean.VolumeInfoBean volumeInfoBean = itemsBean.getVolumeInfo();
			if (volumeInfoBean.getTitle() != null) {
				book.setTitle(volumeInfoBean.getTitle());
			}
			if (volumeInfoBean.getSubtitle() != null) {
				book.setSubtitle(volumeInfoBean.getSubtitle());
			}
			if (volumeInfoBean.getPublishedDate() != null) {
				book.setPublishedDate(volumeInfoBean.getPublishedDate());
			}
			if (volumeInfoBean.getDescription() != null) {
				book.setDescription(volumeInfoBean.getDescription());
			}
			if (volumeInfoBean.getPageCount() > 0) {
				book.setPageCount(volumeInfoBean.getPageCount());
			}
			if (volumeInfoBean.getImageLinks() != null) {
				book.setImageLinks(volumeInfoBean.getImageLinks().getThumbnail());
			}
			if (volumeInfoBean.getAuthors() != null) {
				book.setAuthors(volumeInfoBean.getAuthors());
			}
			if (volumeInfoBean.getPublisher() != null) {
				book.setPublisher(volumeInfoBean.getPublisher());
			}
			if (volumeInfoBean.getIndustryIdentifiers() != null) {
				for (GoogleBooks.ItemsBean.VolumeInfoBean.IndustryIdentifiersBean industryIdentifiersBean :
						volumeInfoBean.getIndustryIdentifiers()
						) {
					if (industryIdentifiersBean.getType().equals("ISBN_10") || industryIdentifiersBean.getType()
							.equals("ISBN_13")) {
						if (industryIdentifiersBean.getIdentifier().length() == 10) {
							book.setIsbn10(industryIdentifiersBean.getIdentifier());
							book.setRegistered(Book.isRegistered(DatabaseColumnLabel.isbn10, book.getIsbn10()));
						}
						if (industryIdentifiersBean.getIdentifier().length() == 13) {
							book.setIsbn13(industryIdentifiersBean.getIdentifier());
							if (!book.isRegistered()) {
								book.setRegistered(Book.isRegistered(DatabaseColumnLabel.isbn13, book.getIsbn13()));
							}
						}
					}else {
						book.setRegistered(false);
					}

				}
			} else {
				book.setRegistered(false);
			}
			bookList.add(book);
		}
		return bookList;
	}

	public List<Book> search() {
		List<Book> bookList = null;
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(this.uri);
			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity entity = httpResponse.getEntity();
				String googleJson = EntityUtils.toString(entity, CHARSET);
				// Data-binding Json --> JavaBean
				ObjectMapper mapper = new ObjectMapper();
				GoogleBooks googleBooks = null;
				try {
					googleBooks = mapper.readValue(googleJson, GoogleBooks.class);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Resolve to Book
				if (googleBooks != null && googleBooks.getTotalItems() > 0) {
					bookList = parseBook(googleBooks);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bookList;
	}
}

