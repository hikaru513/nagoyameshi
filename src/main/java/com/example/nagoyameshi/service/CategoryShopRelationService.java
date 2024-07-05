package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.CategoryShopRelation;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.repository.CategoryShopRelationRepository;

@Service
public class CategoryShopRelationService {
    private final CategoryShopRelationRepository categoryShopRelationRepository;

    public CategoryShopRelationService(CategoryShopRelationRepository categoryShopRelationRepository) {
        this.categoryShopRelationRepository = categoryShopRelationRepository;
    }

    // カテゴリーとショップの関係を登録・追加するメソッド
    @Transactional
    public void subscribe(Category category, Shop shop) {
        CategoryShopRelation categoryShopRelation = new CategoryShopRelation();
        categoryShopRelation.setCategory(category);
        categoryShopRelation.setShop(shop);
        categoryShopRelationRepository.save(categoryShopRelation);
    }

}