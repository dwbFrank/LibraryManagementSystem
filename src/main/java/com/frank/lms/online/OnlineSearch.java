package com.frank.lms.online;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.lms.Book;
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

public class OnlineSearch {

	private static final String HOST = "www.googleapis.com";
	private static final String PATH = "/books/v1/volumes";
	private static final Charset ENCODE = Charset.forName("UTF-8");
	private static final String SCHEME = "https";
	private List<NameValuePair> nvps;
	private String url;

	public OnlineSearch(SearchTerm terms, String search) {
		this.nvps = new ArrayList<>();
		this.nvps.add(new BasicNameValuePair("q", terms.getAbbreviation() + ":" + search));
		this.nvps.add(new BasicNameValuePair("fields", "items(volumeInfo/title,volumeInfo/subtitle," +
				"volumeInfo/authors," +
				"volumeInfo/publishedDate,volumeInfo/description,volumeInfo/industryIdentifiers/*," +
				"volumeInfo/pageCount,volumeInfo/imageLinks/thumbnail,volumeInfo/publisher)"));
		this.nvps.add(new BasicNameValuePair("maxResults", "5"));
	}

	private Book parseBook(GoogleBooks googleBooks) {
		String title = "見付けません";
		String subtitle = "見付けません";
		String publishedDate = "見付けません";
		String description = "見付けません";
		String publisher = "見付けません";
		int pageCount = 0;
		String imageLinks = "見付けません";
		List<String> authors = new ArrayList<>();
		authors.add("見付けません");
		String isbn10 = "見付けません";
		String isbn13 = "見付けません";
		final String ISBN_10 = "ISBN_10";
		final String ISBN_13 = "ISBN_13";

		if (googleBooks.getItems() != null) {
			for (GoogleBooks.ItemsBean item : googleBooks.getItems()
					) {
				GoogleBooks.ItemsBean.VolumeInfoBean volumeInfoBean = item.getVolumeInfo();
				if (volumeInfoBean.getTitle() != null) {
					title = volumeInfoBean.getTitle();
				}
				if (volumeInfoBean.getSubtitle() != null) {
					subtitle = volumeInfoBean.getSubtitle();
				}
				if (volumeInfoBean.getPublishedDate() != null) {
					publishedDate = volumeInfoBean.getPublishedDate();
				}
				if (volumeInfoBean.getDescription() != null) {
					description = volumeInfoBean.getDescription();
				}
				pageCount = volumeInfoBean.getPageCount();
				if (volumeInfoBean.getImageLinks() != null) {
					imageLinks = volumeInfoBean.getImageLinks().getThumbnail();
				}
				if (volumeInfoBean.getAuthors() != null) {
					authors.clear();
					authors.addAll(volumeInfoBean.getAuthors());
				}
				if (volumeInfoBean.getPublisher() != null) {
					publisher = volumeInfoBean.getPublisher();
				}
				if (volumeInfoBean.getIndustryIdentifiers() != null) {
					for (GoogleBooks.ItemsBean.VolumeInfoBean.IndustryIdentifiersBean industryIdentifiersBean :
							volumeInfoBean.getIndustryIdentifiers()
							) {
						if (industryIdentifiersBean.getType().equals(ISBN_10))
							isbn10 = industryIdentifiersBean.getIdentifier();
						if (industryIdentifiersBean.getType().equals(ISBN_13))
							isbn13 = industryIdentifiersBean.getIdentifier();
					}
				}
			}
		}

		return new Book(title, subtitle, publishedDate, description, pageCount, imageLinks, publisher, authors, isbn10,
				isbn13, this.url);
	}

	public Book search() throws IOException, URISyntaxException {
		String context;
		Book book = new Book();
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			URI uri = new URIBuilder().setCharset(ENCODE).setScheme(SCHEME).setHost(HOST).setPath(PATH).setParameters
					(nvps).build();
			this.url = uri.toString();
			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity entity = httpResponse.getEntity();
				context = EntityUtils.toString(entity, ENCODE);
			} else {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			}
		}
		// Fetch Json result
		if (context.length() > 2) {
			// Data-binding Json --> JavaBean
			ObjectMapper mapper = new ObjectMapper();
			GoogleBooks json = mapper.readValue(context, GoogleBooks.class);

			// Resolve to Book
			return parseBook(json);
		}
		return book;
	}
}

