package library.books.service;

import java.util.List;
import library.books.model.Book;

public interface BookService {
    Book save(Book book);

    Book get(Long id);

    void delete(Long id);

    List<Book> findAllByAuthorName(String authorName);

    Book findTopBySoldAmountAndAuthorName(String authorName);

    Book findTopByPublishedAmountAndAuthorName(String authorName);

    List<Book> findAllBySoldAmountAndAuthorNameContains(String partName);

    List<Book> findAllByPublishedAmountAndAuthorNameContains(String partName);

    List<Book> findAllBySuccessRateAndAuthorNameContains(String partName);
}
