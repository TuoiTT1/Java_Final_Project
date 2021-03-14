package com.vn.book_store_server.service.impl;

import com.vn.book_store_server.dto.BookDTO;
import com.vn.book_store_server.model.Book;
import com.vn.book_store_server.model.Category;
import com.vn.book_store_server.repository.BookRepository;
import com.vn.book_store_server.repository.CategoryRepository;
import com.vn.book_store_server.service.BookService;
import com.vn.book_store_server.service.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO dto) {
        if (dto == null)
            return null;
        Book book = this.bookRepository.save(bookMapper.toModel(dto));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO dto) {
        Book book = bookRepository.findById(dto.getId()).orElse(null);
        if (book == null)
            return null;
        book = bookRepository.save(bookMapper.toModel(dto));

        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAllBook(String nameSearch) {
        if(nameSearch == null || "".equals(nameSearch))
            return this.toDtos(bookRepository.findAll());
        return this.toDtos(bookRepository.findBookByNameContaining(nameSearch));
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteBook(BookDTO bookDTO) {
        bookRepository.delete(this.bookMapper.toModel(bookDTO));
    }

    @Override
    public BookDTO findById(Integer id) {
        if (id == null)
            return null;
        Optional<Book> book = this.bookRepository.findById(id);
        return book.map(this.bookMapper::toDTO).orElse(null);
    }

    @Override
    public List<BookDTO> getByCategoryId(Integer idCategory) {
        List<BookDTO> list = new ArrayList<>();
        Category category = this.categoryRepository.findById(idCategory).orElse(null);
        if (category == null)
            return list;
        List<Book> books = this.bookRepository.findBookByCategory(category);

        return this.toDtos(books);
    }

    private List<BookDTO> toDtos(List<Book> books) {
        List<BookDTO> list = new ArrayList<>();

        if (books == null)
            return list;

        for (Book book : books) {
            list.add(bookMapper.toDTO(book));
        }
        return list;
    }
}
