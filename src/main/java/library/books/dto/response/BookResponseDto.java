package library.books.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
    private Long id;
    private List<Long> authorIds;
    private int publishedAmount;
    private int soldAmount;
}
