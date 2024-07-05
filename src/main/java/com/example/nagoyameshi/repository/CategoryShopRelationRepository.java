package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nagoyameshi.entity.CategoryShopRelation;

@Repository
public interface CategoryShopRelationRepository extends JpaRepository<CategoryShopRelation, Integer> {
    List<CategoryShopRelation> findByShopId(Integer shopId);
}