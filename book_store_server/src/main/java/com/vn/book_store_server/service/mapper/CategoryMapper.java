package com.vn.book_store_server.service.mapper;

import com.vn.book_store_server.dto.CategoryDTO;
import com.vn.book_store_server.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryMapper {
    @Mapping(target = "bookList", ignore = true)
    public CategoryDTO toDto(Category model);
    @Mapping(target = "bookList", ignore = true)
    public Category toModel(CategoryDTO dto);
    
    default Category fromId(Integer id){
        if(id == null)
            return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
