package library.books.service.impl;

import java.util.List;
import library.books.model.Author;
import library.books.model.Book;
import library.books.repository.AuthorRepository;
import library.books.repository.BookRepository;
import library.books.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author get(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        Author authorFromDB = authorRepository.getReferenceById(id);
        List<Book> booksByAuthors = bookRepository.findBooksByAuthorId(authorFromDB.getId());
        for (Book book : booksByAuthors) {
            book.getAuthors().remove(authorFromDB);
            bookRepository.save(book);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public Author findTopBySuccessAuthorRate() {
        return authorRepository.findTopBySuccessAuthorRate();
    }
}
