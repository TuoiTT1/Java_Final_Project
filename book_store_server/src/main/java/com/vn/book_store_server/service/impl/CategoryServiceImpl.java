package com.vn.book_store_server.service.impl;

import com.vn.book_store_server.dto.CategoryDTO;
import com.vn.book_store_server.model.Category;
import com.vn.book_store_server.repository.CategoryRepository;
import com.vn.book_store_server.service.CategoryService;
import com.vn.book_store_server.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.toDtos(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO findById(Integer id) {
        if (id == null)
            return null;
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this.categoryMapper::toDto).orElse(null);

    }

    private List<CategoryDTO> toDtos(List<Category> models) {
        List<CategoryDTO> list = new ArrayList<>();
        if (models == null)
            return list;
        for (Category category : models) {
            list.add(categoryMapper.toDto(category));
        }
        return list;
    }
}
