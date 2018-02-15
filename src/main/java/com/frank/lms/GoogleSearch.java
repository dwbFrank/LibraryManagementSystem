package com.frank.lms;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleSearch {
    enum KeyWord {
        title("intitle"), author("inauthor"), publisher("inpublisher"), subject("subject"), isbn("isbn"),
        lccn("lccn"), oclc("oclc");
        private String abbreviation;

        public String getAbbreviation() {
            return abbreviation;
        }

        KeyWord(String abbreviation) {
            this.abbreviation = abbreviation;
        }
    }

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GOOGLE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String HTTP_METHOD = "GET";
    private static final String ENCODE = "US-ASCII";
    private static final String PARTIAL = "&fields=items(volumeInfo/title,volumeInfo/subtitle,volumeInfo/authors," +
            "volumeInfo/publishedDate,volumeInfo/description,volumeInfo/industryIdentifiers/*,volumeInfo/pageCount," +
            "volumeInfo/imageLinks/*)&maxResults=2";
    private String terms;
    private String search;

    public GoogleSearch(KeyWord terms, String search) {
        this.terms = terms.getAbbreviation();
        this.search = search;
    }


    public String getTerm() {
        return terms;
    }

    public JsonItems query() throws IOException {
        String query = search + "+" + terms;
        String urlString = GOOGLE_URL + URLEncoder.encode(query, ENCODE);
        URL url = new URL(urlString + PARTIAL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HTTP_METHOD);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;


            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
        }
        connection.disconnect();
        ObjectMapper mapper = new ObjectMapper();
        JsonItems googleBook;
        googleBook = mapper.readValue(response.toString(), JsonItems.class);
        return googleBook;
    }

}

