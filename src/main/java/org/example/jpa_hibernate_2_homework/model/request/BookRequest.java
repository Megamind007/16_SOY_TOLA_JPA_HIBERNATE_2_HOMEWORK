package org.example.jpa_hibernate_2_homework.model.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
    private String title;
    private String description;
    private String author;
    private LocalDate publicationYear;
}
