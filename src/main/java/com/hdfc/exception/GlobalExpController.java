package com.hdfc.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExpController implements ExceptionMapper<BookNotFoundExp> {

	
	public Response toResponse(BookNotFoundExp e){
		return Response.ok("Oops..Book not found...").status(Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).build();
	}

	
}
