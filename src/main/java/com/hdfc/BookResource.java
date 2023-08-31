package com.hdfc;


import java.util.List;
import java.util.Objects;

import com.hdfc.entity.Book;
import com.hdfc.exception.BookNotFoundExp;
import com.hdfc.service.IBookservice;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	
	@Inject
	IBookservice bookservice;
	
	@GET
	@Path("/book") // book
	public List<Book>getAllBooks(){
		return bookservice.getAllBook();
	}
	
	@POST
	@Path("/book") //book
	public Book addBook(Book b) {
		return bookservice.addBook(b);
	}
	
	@PUT
	@Path("/book/{id}") //book
	public Book updateBook(@PathParam("id") long id,Book b) throws BookNotFoundExp {
		return bookservice.updateBook(id,b);
	}
	
	@DELETE
	@Path("/book/{id}") //book/id
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") long id)  throws BookNotFoundExp {
	    bookservice.delete(id);
		return Response.ok("Record Deleted Successfully!").build();
	}
	
	@GET
	@Path("/book/{id}") //book/id
	public Book getByID(@PathParam("id") long id)  throws BookNotFoundExp {
		return bookservice.getByID(id);
		
	}
}
