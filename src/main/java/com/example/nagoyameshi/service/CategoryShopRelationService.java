package com.example.nagoyameshi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.CategoryShopRelation;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.CategoryShopRelationRepository;

@Service
public class CategoryShopRelationService {
    private final CategoryShopRelationRepository categoryShopRelationRepository;
    private final CategoryRepository categoryRepository; 

    public CategoryShopRelationService(CategoryShopRelationRepository categoryShopRelationRepository, CategoryRepository categoryRepository) {
        this.categoryShopRelationRepository = categoryShopRelationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void create(List<Integer> categoryIds, Shop shop) {
        for (Integer categoryId : categoryIds) {
            if (categoryId == null) {
                continue;
            }
            
            CategoryShopRelation categoryShopRelation = new CategoryShopRelation();
            Category category = categoryRepository.getReferenceById(categoryId);
            
            categoryShopRelation.setShop(shop);
            categoryShopRelation.setCategory(category);
            
            categoryShopRelationRepository.save(categoryShopRelation);            
        }
    }
  }