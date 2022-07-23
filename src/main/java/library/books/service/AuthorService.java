package library.books.service;

import library.books.model.Author;

public interface AuthorService {
    Author save(Author author);

    Author get(Long id);

    void delete(Long id);

    Author findTopBySuccessAuthorRate();
}
