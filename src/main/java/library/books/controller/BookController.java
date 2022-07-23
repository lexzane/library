package library.books.controller;

import java.util.List;
import java.util.stream.Collectors;
import library.books.dto.request.BookRequestDto;
import library.books.dto.response.BookResponseDto;
import library.books.model.Book;
import library.books.service.BookService;
import library.books.service.mapper.RequestDtoMapper;
import library.books.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final RequestDtoMapper<BookRequestDto, Book> requestDtoMapper;
    private final ResponseDtoMapper<BookResponseDto, Book> responseDtoMapper;

    public BookController(BookService bookService,
                          RequestDtoMapper<BookRequestDto, Book> requestDtoMapper,
                          ResponseDtoMapper<BookResponseDto, Book> responseDtoMapper) {
        this.bookService = bookService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public BookResponseDto create(@RequestBody BookRequestDto dto) {
        Book book = bookService.save(requestDtoMapper.mapToModel(dto));
        return responseDtoMapper.mapToDto(book);
    }

    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        Book book = requestDtoMapper.mapToModel(dto);
        book.setId(id);
        Book newBook = bookService.save(book);
        return responseDtoMapper.mapToDto(newBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/authors")
    public List<BookResponseDto> findAllByAuthorName(@RequestParam String name) {
        return bookService.findAllByAuthorName(name)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/top-sold/authors")
    public BookResponseDto findTopBySoldAmountAndAuthorName(@RequestParam String name) {
        Book book = bookService.findTopBySoldAmountAndAuthorName(name);
        return responseDtoMapper.mapToDto(book);
    }

    @GetMapping("/top-published/authors")
    public BookResponseDto findTopByPublishedAmountAndAuthorName(@RequestParam String name) {
        Book book = bookService.findTopByPublishedAmountAndAuthorName(name);
        return responseDtoMapper.mapToDto(book);
    }

    @GetMapping("/all-top-sold/authors")
    public List<BookResponseDto> findAllBySoldAmountAndAuthorNameContains(
            @RequestParam String partName) {
        return bookService.findAllBySoldAmountAndAuthorNameContains(partName)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/all-top-published/authors")
    public List<BookResponseDto> findAllByPublishedAmountAndAuthorNameContains(
            @RequestParam String partName) {
        return bookService.findAllByPublishedAmountAndAuthorNameContains(partName)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-success-rate/authors")
    public List<BookResponseDto> findAllBySuccessRateAndAuthorNameContains(
            @RequestParam String partName) {
        return bookService.findAllBySuccessRateAndAuthorNameContains(partName)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
