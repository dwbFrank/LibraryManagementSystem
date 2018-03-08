package com.frank.lms.online;

public enum GoogleSearchTerm {
	title("intitle"),
	author("inauthor"),
	publisher("inpublisher"),
	subject("subject"),
	isbn("isbn"),
	lccn("lccn"),
	oclc("oclc");
	private String term;

	GoogleSearchTerm(String term) {
		this.term = term;
	}

	public String getAbbreviation() {
		return term;
	}
}
