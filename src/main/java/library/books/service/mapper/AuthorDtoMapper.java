package library.books.service.mapper;

import library.books.dto.request.AuthorRequestDto;
import library.books.dto.response.AuthorResponseDto;
import library.books.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoMapper implements RequestDtoMapper<AuthorRequestDto, Author>,
        ResponseDtoMapper<AuthorResponseDto, Author> {
    @Override
    public Author mapToModel(AuthorRequestDto dto) {
        Author author = new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setBirthDate(dto.getBirthDate());
        author.setPhone(dto.getPhone());
        author.setEmail(dto.getEmail());
        return author;
    }

    @Override
    public AuthorResponseDto mapToDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setAuthorName(author.getAuthorName());
        authorResponseDto.setBirthDate(author.getBirthDate());
        authorResponseDto.setPhone(authorResponseDto.getPhone());
        authorResponseDto.setEmail(authorResponseDto.getEmail());
        return authorResponseDto;
    }
}
