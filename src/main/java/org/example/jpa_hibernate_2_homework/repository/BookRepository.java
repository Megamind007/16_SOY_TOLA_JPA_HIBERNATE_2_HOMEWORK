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
        String query = "select b from Book b";
        return entityManager.createQuery(query).getResultList();
    }

    public Book readBookById(UUID id) {
        return  entityManager.find(Book.class, id);
    }

    public List<Book> readBookByTitle(String title) {
        String query = "select b from Book b where b.title like :title";
        return entityManager.createQuery(query, Book.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }


    public Book updateBookById(@PathVariable UUID id, BookRequest bookRequest) {
        Book book = entityManager.find(Book.class, id);
        entityManager.detach(book);
        modelMapper.map(bookRequest, book);
        entityManager.merge(book);
        return book;
    }

    public Boolean deleteBookById(UUID id) {
        Book book = entityManager.find(Book.class, id);
        if(book!=null){
            entityManager.remove(book);
            return true;
        }
        return false;
    }
}


