package com.social.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.social.web.model.Book;

@Path("/book")
public class BookResource {

	@Path("/get")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getBookName(){
		System.out.println("called rest service");
		return "Got it!";
	}
	
	@Path("/{id}")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})	
	public Book getBookById(@PathParam("id") String id){
		
		Book book= new Book();
		book.setTitle("Tutorial on Spring Social");
		book.setNoOfPages(12);
		return book;
		
	}
}
