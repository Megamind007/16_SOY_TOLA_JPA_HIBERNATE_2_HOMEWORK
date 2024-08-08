package org.example.jpa_hibernate_2_homework.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.jpa_hibernate_2_homework.model.Book;
import org.example.jpa_hibernate_2_homework.model.request.BookRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@AllArgsConstructor
public class BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public Book insertBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        entityManager.persist(book);
        return book;
    }
    public List<?> readAllBooks() {
        return entityManager.createQuery("select b from Book b").getResultList();
    }

    public Book readBookById(UUID id) {
        Book book = entityManager.find(Book.class, id);
        System.out.println(book.toString());
        return book;
    }

    public List<Book> readBookByTitle(String title) {
        return entityManager.createQuery("select b from Book b where b.title like :title", Book.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }


    public Book updateBookById(@PathVariable UUID id, BookRequest bookRequest) {
        Book book = entityManager.find(Book.class, id);
        entityManager.detach(book);
        System.out.println(book.toString());
        modelMapper.map(bookRequest, book);
        entityManager.merge(book);
        return book;
    }

    public Book deleteBookById(UUID id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        System.out.println("Book deleted: " + book.toString());
        return book;
    }
}


