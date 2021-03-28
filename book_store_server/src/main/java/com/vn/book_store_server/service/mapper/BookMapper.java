package com.vn.book_store_server.service.mapper;

import com.vn.book_store_server.dto.BookDTO;
import com.vn.book_store_server.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookMapper.class, CategoryMapper.class})
public interface BookMapper {
    @Mapping(source = "category.id", target = "categoryId")
    BookDTO toDTO(Book model);

    @Mapping(source = "categoryId", target = "category")
    Book toModel(BookDTO dto);

    default Book fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
