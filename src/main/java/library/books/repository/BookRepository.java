package library.books.repository;

import java.util.List;
import library.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "b.published_amount, b.sold_amount "
            + "FROM books "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE ba.author_name = ?1", nativeQuery = true)
    List<Book> findAllByAuthorName(String authorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "b.published_amount, MAX(b.sold_amount) AS sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.author_name = ?1", nativeQuery = true)
    Book findTopBySoldAmountAndAuthorName(String authorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "MAX(b.published_amount) AS published_amount, b.sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.author_name = ?1", nativeQuery = true)
    Book findTopByPublishedAmountAndAuthorName(String authorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "b.published_amount, MAX(b.sold_amount) AS sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.author_name LIKE %?1% "
            + "GROUP BY a.author_name", nativeQuery = true)
    List<Book> findAllByTopSoldAmountAndAuthorNameContains(String partAuthorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "MAX(b.published_amount) AS published_amount, b.sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.author_name LIKE %?1% "
            + "GROUP BY a.author_name", nativeQuery = true)
    List<Book> findAllByTopPublishedAmountAndAuthorNameContains(String partAuthorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "b.published_amount, b.sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.author_name LIKE %?1% "
            + "GROUP BY a.author_name "
            + "HAVING MAX(b.sold_amount / b.published_amount)", nativeQuery = true)
    List<Book> findAllBySuccessRateAndAuthorNameContains(String partAuthorName);

    @Query(value = "SELECT b.id, a.id, a.author_name, a.birth_date, a.email, a.phone, "
            + "b.published_amount, b.sold_amount "
            + "FROM books b "
            + "JOIN books_authors ba on b.id = ba.book_id "
            + "JOIN authors a ON a.id = ba.author_id "
            + "WHERE a.id = ?1", nativeQuery = true)
    List<Book> findBooksByAuthorId(Long id);
}
