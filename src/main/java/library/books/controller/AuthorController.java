package library.books.controller;

import library.books.dto.request.AuthorRequestDto;
import library.books.dto.response.AuthorResponseDto;
import library.books.model.Author;
import library.books.service.AuthorService;
import library.books.service.mapper.RequestDtoMapper;
import library.books.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final RequestDtoMapper<AuthorRequestDto, Author> requestDtoMapper;
    private final ResponseDtoMapper<AuthorResponseDto, Author> responseDtoMapper;

    public AuthorController(AuthorService authorService,
                            RequestDtoMapper<AuthorRequestDto, Author> requestDtoMapper,
                            ResponseDtoMapper<AuthorResponseDto, Author> responseDtoMapper) {
        this.authorService = authorService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public AuthorResponseDto create(@RequestBody AuthorRequestDto dto) {
        Author author = authorService.save(requestDtoMapper.mapToModel(dto));
        return responseDtoMapper.mapToDto(author);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto update(@PathVariable Long id, @RequestBody AuthorRequestDto dto) {
        Author author = requestDtoMapper.mapToModel(dto);
        author.setId(id);
        Author newAuthor = authorService.save(author);
        return responseDtoMapper.mapToDto(newAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

    @GetMapping("/top-author")
    public AuthorResponseDto findTopBySuccessAuthorRate() {
        Author author = authorService.findTopBySuccessAuthorRate();
        return responseDtoMapper.mapToDto(author);
    }
}
