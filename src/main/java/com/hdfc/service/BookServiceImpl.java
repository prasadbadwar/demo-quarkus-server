package com.hdfc.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.hdfc.entity.Book;
import com.hdfc.exception.BookNotFoundExp;
import com.hdfc.repository.BookRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BookServiceImpl implements IBookservice {

	@Inject
	BookRepository bookrepo;
	
	@Override
	@Transactional
	public Book addBook(Book b) {
	
		bookrepo.persist(b);
		return b;
	}

	@Override
	@Transactional
	public Book updateBook(long id,Book b) throws BookNotFoundExp {
		Optional<Book> opBook=bookrepo.findByIdOptional(id);
		if(opBook.isPresent()) {
			Book dbBook=opBook.get();
			if(Objects.nonNull(b.getTitle())) {
				dbBook.setTitle(b.getTitle());
			}
			if(Objects.nonNull(b.getAuthor())) {
				dbBook.setAuthor(b.getAuthor());
			}
			if(Objects.nonNull(b.getPrice())) {
				dbBook.setPrice(b.getPrice());
			}
			
			bookrepo.persist(dbBook);
			
			
		}
		else {
		throw new BookNotFoundExp();	
		}
		return b;
	}

	@Override
	public List<Book> getAllBook() {
		return bookrepo.listAll();
	}

	@Override
	@Transactional
	public void delete(long id) throws BookNotFoundExp {
		bookrepo.deleteById(id);
		
		
	}

	@Override
	public Book getByID(long id) throws BookNotFoundExp {
		Book book=bookrepo.findById(id);
		if(book==null) {
			throw new BookNotFoundExp();
		}
		return book;
	}
}
