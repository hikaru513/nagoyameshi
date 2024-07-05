package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.repository.CategoryRepository;


@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	// カテゴリを保存または更新するメソッド
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
  
//  @Transactional
  public void deleteById(Integer id) {
      categoryRepository.deleteById(id);

  }
}

