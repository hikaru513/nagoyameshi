package com.example.nagoyameshi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.repository.CategoryRepository;


@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
		
	}
	
	// カテゴリを全件取得
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // 新規カテゴリを保存
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}

