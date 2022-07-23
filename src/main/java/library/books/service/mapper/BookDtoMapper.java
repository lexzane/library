package library.books.service.mapper;

import java.util.stream.Collectors;
import library.books.dto.request.BookRequestDto;
import library.books.dto.response.BookResponseDto;
import library.books.model.Author;
import library.books.model.Book;
import library.books.service.AuthorService;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapper implements RequestDtoMapper<BookRequestDto, Book>,
        ResponseDtoMapper<BookResponseDto, Book> {
    private final AuthorService authorService;

    public BookDtoMapper(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Book mapToModel(BookRequestDto dto) {
        Book book = new Book();
        book.setAuthors(dto.getAuthorIds()
                .stream()
                .map(authorService::get)
                .collect(Collectors.toList()));
        book.setPublishedAmount(dto.getPublishedAmount());
        book.setSoldAmount(dto.getSoldAmount());
        return book;
    }

    @Override
    public BookResponseDto mapToDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setAuthorIds(book.getAuthors()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toList()));
        bookResponseDto.setPublishedAmount(book.getPublishedAmount());
        bookResponseDto.setSoldAmount(book.getSoldAmount());
        return bookResponseDto;
    }
}
