package com.social.web.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	private String title;
	private int noOfPages;
	
	public int getNoOfPages() {
		return noOfPages;
	}
	public String getTitle() {
		return title;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
