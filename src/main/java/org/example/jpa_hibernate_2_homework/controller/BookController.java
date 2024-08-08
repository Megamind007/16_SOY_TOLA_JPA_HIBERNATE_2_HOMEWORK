package org.example.jpa_hibernate_2_homework.controller;


import lombok.AllArgsConstructor;

import org.example.jpa_hibernate_2_homework.model.request.BookRequest;
import org.example.jpa_hibernate_2_homework.model.response.ApiResponse;
import org.example.jpa_hibernate_2_homework.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> readBookById(@PathVariable UUID id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Read book successfully")
                .payload(bookRepository.readBookById(id))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
    public ResponseEntity<?> deleteBookById(@PathVariable UUID id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete book by id successfully")
                .payload(bookRepository.deleteBookById(id))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
