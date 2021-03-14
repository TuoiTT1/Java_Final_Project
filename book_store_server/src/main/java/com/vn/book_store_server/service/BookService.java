package com.vn.book_store_server.service;

import com.vn.book_store_server.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookDTO createBook(BookDTO dto);

    BookDTO updateBook(BookDTO dto);

    List<BookDTO> getAllBook(String nameSearch);

    void deleteById(Integer id);

    void deleteBook(BookDTO bookDTO);

    BookDTO findById(Integer id);

    List<BookDTO> getByCategoryId(Integer idCategory);
}
