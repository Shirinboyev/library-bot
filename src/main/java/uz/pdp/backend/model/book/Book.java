package uz.pdp.backend.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {
private String name;
private String author;
private String bookLink;
private String photoLink;
private String Genre;
}
