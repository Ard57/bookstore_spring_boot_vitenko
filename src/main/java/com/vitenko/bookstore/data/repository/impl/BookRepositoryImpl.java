package com.vitenko.bookstore.data.repository.impl;

import com.vitenko.bookstore.data.entity.Book;
import com.vitenko.bookstore.data.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.vitenko.bookstore.exception.book.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        TypedQuery<Book> query = entityManager.createQuery("from Book where isbn=:isbn", Book.class);
        query.setParameter("isbn", isbn);
        List<Book> books = query.getResultList();
        if (books.size() > 0) {
            return books.get(0);
        } else {
            throw new BookNotFoundException("Book with ISBN " + isbn + " was not found");
        }
    }

    @Override
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = entityManager.createQuery("from Book where author=:author", Book.class);
        query.setParameter("author", author);
        List<Book> books = query.getResultList();
        return books;
    }
    @Override
    public Book save(Book book) {
        if (book.getId() != null) {
            entityManager.merge(book);
        } else {
            entityManager.persist(book);
        }
        entityManager.detach(book);
        return book;
    }

    @Override
    public Book findById(Long id) throws BookNotFoundException{
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new BookNotFoundException("Book with id " + id + " was not found.");
        }
        entityManager.detach(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = entityManager.createQuery("from Book ", Book.class).getResultList();
        books.forEach(entityManager::detach);
        return books;
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
            deleted = true;
        }
        return deleted;
    }
}
