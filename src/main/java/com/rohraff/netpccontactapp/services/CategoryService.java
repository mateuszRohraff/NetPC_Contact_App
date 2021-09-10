package com.rohraff.netpccontactapp.services;

import com.rohraff.netpccontactapp.mapper.CategoryMapper;
import com.rohraff.netpccontactapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }
}
