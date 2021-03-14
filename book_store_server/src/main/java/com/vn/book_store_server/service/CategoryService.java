package com.vn.book_store_server.service;

import com.vn.book_store_server.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO findById(Integer id);

}
