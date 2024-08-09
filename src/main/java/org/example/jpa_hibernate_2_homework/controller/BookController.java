package org.example.jpa_hibernate_2_homework.controller;


import lombok.AllArgsConstructor;

import org.example.jpa_hibernate_2_homework.model.Book;
import org.example.jpa_hibernate_2_homework.model.request.BookRequest;
import org.example.jpa_hibernate_2_homework.model.response.ApiResponse;
import org.example.jpa_hibernate_2_homework.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    @PostMapping
    public ResponseEntity<?> insertBook(@RequestBody BookRequest bookRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Book inserted successfully")
                .payload(bookRepository.insertBook(bookRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> readAllBooks() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Book inserted successfully")
                .payload(bookRepository.readAllBooks())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> readBookById(@PathVariable UUID id) {
        Optional<Book> book = bookRepository.readBookById(id);
        return book.map(book1 -> {
            ApiResponse<Book> response = ApiResponse.<Book>builder()
                    .message("Read book successfully")
                    .payload(book1)
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }).orElseGet(() -> {
            ApiResponse<Book> response = ApiResponse.<Book>builder()
                    .message("Book not found")
                    .payload(null)
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        });
    }


    @GetMapping("/title")
    public ResponseEntity<?> readBookByTitle(@RequestParam String title) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Read books by title successfully")
                .payload(bookRepository.readBookByTitle(title))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable UUID id,@RequestBody BookRequest bookRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Update book by id successfully")
                .payload(bookRepository.updateBookById(id,bookRequest))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteBookById(@PathVariable UUID id) {
        Boolean isDelete =  bookRepository.deleteBookById(id);
        if(isDelete){
            ApiResponse<?> response = ApiResponse.builder()
                    .message("Delete book successfully")
                    .payload(null)
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .time(LocalDateTime.now())
                    .build();
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }{
            ApiResponse<?> response = ApiResponse.builder()
                    .message("Book not found")
                    .payload(null)
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .time(LocalDateTime.now())
                    .build();
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
