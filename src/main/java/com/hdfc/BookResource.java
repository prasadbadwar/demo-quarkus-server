package com.hdfc;


import java.util.List;

import org.jboss.logging.Logger;

import com.hdfc.entity.Book;
import com.hdfc.exception.BookNotFoundExp;
import com.hdfc.service.IBookservice;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
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
	@Inject
	SecurityIdentity identity;
	
	
	private static final Logger log=Logger.getLogger(BookResource.class);
	
	@GET
	@RolesAllowed({"viewer","admin"})
	@Path("/book") // book
	public List<Book>getAllBooks(){
		log.info(identity.getPrincipal().getName()+" is accessing list of books");
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
	@RolesAllowed("viewer")
	public Book getByID(@PathParam("id") long id)  throws BookNotFoundExp {
		log.info("Displaying information of Book ID: "+id);
		return bookservice.getByID(id);
		
	}
}
