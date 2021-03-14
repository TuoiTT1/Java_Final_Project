package com.vn.book_store_server.controller;

import com.vn.book_store_server.dto.CategoryDTO;
import com.vn.book_store_server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/api/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        System.out.println("start get all categories: api/category");
        List<CategoryDTO> list;
        list = categoryService.getAllCategories();

        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/api/categories/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable String id) {
        System.out.println("start get Category by id: api/category/" + id);
        try {
            Integer idCategory = Integer.valueOf(id);
            CategoryDTO categoryDTO = categoryService.findById(idCategory);
            System.out.println(categoryDTO);
            if (categoryDTO == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
