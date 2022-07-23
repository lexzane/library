package library.books.dto.request;

import java.util.List;
import javax.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SqlResultSetMapping(name = "BookRequestDto")
public class BookRequestDto {
    private List<Long> authorIds;
    private int publishedAmount;
    private int soldAmount;
}
