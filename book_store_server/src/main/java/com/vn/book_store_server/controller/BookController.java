package com.vn.book_store_server.controller;

import com.vn.book_store_server.dto.BookDTO;
import com.vn.book_store_server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(@RequestParam(required = false) String nameSearch) {
        System.out.println("start get all books: api/books");
        List<BookDTO> books;

        try {
            books = bookService.getAllBook(nameSearch);
        } catch (Exception e) {
            books = new ArrayList<>();
            e.printStackTrace();

        }

        System.out.println(books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/{idCategory}/books")
    public ResponseEntity<List<BookDTO>> getByCategory(@PathVariable String idCategory) {
        System.out.println("start get books by category: api/" + idCategory + "/books");
        List<BookDTO> books;
        try {
            books = bookService.getByCategoryId(Integer.valueOf(idCategory));
        } catch (Exception e) {
            books = new ArrayList<>();
            e.printStackTrace();
        }

        System.out.println(books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        System.out.println("start getBookById: api/books/" + id);
        int idBook;
        try {
            idBook = Integer.parseInt(id);
            BookDTO bookDTO = bookService.findById(idBook);
            System.out.println(bookDTO);
            if (bookDTO == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        System.out.println("start create a book: " + bookDTO);
        if (bookDTO == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        try {
            bookDTO = bookService.createBook(bookDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bookDTO == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @PutMapping("/api/books")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        System.out.println("start update a book: " + bookDTO);
        try {
            bookDTO = bookService.updateBook(bookDTO);
            if (bookDTO == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Integer id) {
        System.out.println("start delete a book by id: " + id);
        BookDTO bookDTO = bookService.findById(id);
        if (bookDTO == null) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        try {
            bookDTO = bookService.createBook(bookDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
