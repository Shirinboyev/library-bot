package uz.pdp.backend.model.genreOfBooks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fantastik {
    private String name;
    private String genre;
    private String author;

}
