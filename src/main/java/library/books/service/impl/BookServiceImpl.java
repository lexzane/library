package library.books.service.impl;

import java.util.List;
import library.books.model.Book;
import library.books.repository.BookRepository;
import library.books.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book get(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByAuthorName(String authorName) {
        return bookRepository.findAllByAuthorName(authorName);
    }

    @Override
    public Book findTopBySoldAmountAndAuthorName(String authorName) {
        return bookRepository.findTopBySoldAmountAndAuthorName(authorName);
    }

    @Override
    public Book findTopByPublishedAmountAndAuthorName(String authorName) {
        return bookRepository.findTopByPublishedAmountAndAuthorName(authorName);
    }

    @Override
    public List<Book> findAllBySoldAmountAndAuthorNameContains(String partName) {
        return bookRepository.findAllByTopSoldAmountAndAuthorNameContains(partName);
    }

    @Override
    public List<Book> findAllByPublishedAmountAndAuthorNameContains(String partName) {
        return bookRepository.findAllByTopPublishedAmountAndAuthorNameContains(partName);
    }

    @Override
    public List<Book> findAllBySuccessRateAndAuthorNameContains(String partName) {
        return bookRepository.findAllBySuccessRateAndAuthorNameContains(partName);
    }
}
