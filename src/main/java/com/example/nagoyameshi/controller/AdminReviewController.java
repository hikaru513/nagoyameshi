package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.repository.ReviewRepository;
 
 @Controller
 @RequestMapping("/admin/reviews")
public class AdminReviewController {
     private final ReviewRepository reviewRepository;         
     
     public AdminReviewController(ReviewRepository reviewRepository) {
         this.reviewRepository = reviewRepository;                
     }	
     
     @GetMapping
     public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
    	 Page<Review> reviewPage = reviewRepository.findAll(pageable);      
         
    	model.addAttribute("reviewPage", reviewPage);             
         
         return "admin/reviews/index";
     } 
     
     @GetMapping("/{id}")
     public String show(@PathVariable(name = "id") Integer id, Model model) {
         Review review = reviewRepository.getReferenceById(id);
         
         model.addAttribute("review", review);
         
         return "admin/reviews/show";
     }    
}
