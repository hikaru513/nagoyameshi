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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.repository.ShopRepository;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private final ShopRepository shopRepository;        
    
    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;            
    }  

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(name = "area", required = false) String area,
                        @RequestParam(name = "price", required = false) Integer price,
                        @RequestParam(name = "categoryshoprelation", required = false) String categoryshoprelation,
                        @RequestParam(name = "order", required = false) String order,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model) 
    {
        Page<Shop> shopPage;
                
        if (keyword != null && !keyword.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
                shopPage = shopRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
            } else {
                shopPage = shopRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
            }   
        } else if (area != null && !area.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
                shopPage = shopRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
            } else {
                shopPage = shopRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
            }  
        } else if (price != null) {
        	if (order != null && order.equals("priceAsc")) {
                shopPage = shopRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
            } else {
                shopPage = shopRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
            }
        } else {
        	if (order != null && order.equals("priceAsc")) {
                shopPage = shopRepository.findAllByOrderByPriceAsc(pageable);
            } else {
                shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);   
            }  
        }                
        
        model.addAttribute("shopPage", shopPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("area", area);
        model.addAttribute("price", price);
        model.addAttribute("categoryshoprelation", categoryshoprelation);
        model.addAttribute("order", order);
        
        return "shops/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Shop shop = shopRepository.getReferenceById(id);
        
        model.addAttribute("shop", shop);         
        
        return "shops/show";
    }  
}