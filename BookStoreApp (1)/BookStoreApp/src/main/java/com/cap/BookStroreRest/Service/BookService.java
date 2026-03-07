package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.BookDto;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.Entity.Book;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.BookRepository;
import com.cap.BookStroreRest.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;


//    @CacheEvict(value= "books" ,allEntries = true)

    public BookDto createBook(@Valid BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);

        User user = userRepository.findById(bookDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setUser(user);

        Book savedBook = bookRepository.save(book);

        BookDto dto = modelMapper.map(savedBook, BookDto.class);
        dto.setUserId(savedBook.getUser().getId());
        return dto;
    }



    public BookDto updateBookbyId(Long id, BookDto bookDto){
        Book existingbook =  bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book ID is not found"));

        existingbook.setTitle(bookDto.getTitle());
        existingbook.setAuthor(bookDto.getAuthor());
        existingbook.setPrice(bookDto.getPrice());

        Book updatedBook =  bookRepository.save(existingbook);
        return modelMapper.map(updatedBook, BookDto.class);
    }
    //update
    public BookDto getBookById(Long id){
        Book book  =  bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found"));
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto.setUserId(book.getUser().getId());
        return dto;
    }
    //delete
    public BookDto deleteById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);

        BookDto dto = modelMapper.map(book, BookDto.class);
        dto.setUserId(book.getUser().getId());
        return dto;
    }
    public List<BookDto> getAllBooks(){
        List<Book> bookList =  bookRepository.findAll();
        return bookList.stream()
                .map(book -> {
                    BookDto dto = modelMapper.map(book, BookDto.class);
                    dto.setUserId(book.getUser().getId());
                    return dto;
                }).toList();
    }
    public PageResponse<BookDto>  getBooks(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        List<BookDto> dtoList =  bookPage.getContent()
                .stream()
                .map(book -> {
                    BookDto dto = modelMapper.map(book, BookDto.class);
                    dto.setUserId(book.getUser().getId());
                    return dto;
                })
                .toList();
        return new PageResponse<>(
                dtoList,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements(),
                bookPage.getTotalPages()

        );
    }
}
