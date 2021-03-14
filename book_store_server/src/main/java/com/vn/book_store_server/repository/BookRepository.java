package com.vn.book_store_server.repository;

import com.vn.book_store_server.model.Book;
import com.vn.book_store_server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByCategory(Category category);
    List<Book> findBookByNameContaining(String nameSearch);
}
