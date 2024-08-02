package com.example.nagoyameshi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.CategoryShopRelation;
import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.CategoryShopRelationRepository;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository;
	private final ShopRepository shopRepository;
	private final CategoryShopRelationRepository categoryShopRelationRepository;
	
    
    public ReservationController(ReservationRepository reservationRepository, ShopRepository shopRepository, CategoryShopRelationRepository categoryShopRelationRepository) {          
        this.reservationRepository = reservationRepository;
        this.shopRepository = shopRepository;
        this.categoryShopRelationRepository = categoryShopRelationRepository;
    }    

    @GetMapping("/reservations")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        User user = userDetailsImpl.getUser();
        Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        
        model.addAttribute("reservationPage", reservationPage);         
        
        return "reservations/index";
    }
    @GetMapping("/shops/{id}/reservations/input")
    public String input(@PathVariable(name = "id") Integer id,
                        @ModelAttribute @Validated ReservationInputForm reservationInputForm,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model)
    {   
        Shop shop = shopRepository.getReferenceById(id);   
        List<CategoryShopRelation> categoryShopRelation = categoryShopRelationRepository.findByShopOrderByIdAsc(shop);
        
        if (bindingResult.hasErrors()) {            
            model.addAttribute("shop", shop);            
            model.addAttribute("categoryShopRelation", categoryShopRelation);
            model.addAttribute("errorMessage", "予約内容に不備があります。"); 
            return "shops/show";
        }
        
        redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);           
        
        return "redirect:/shops/{id}/reservations/confirm";
    }    
    
    @GetMapping("/shops/{id}/reservations/confirm")
    public String confirm(
        @PathVariable(name = "id") Integer id,
        @ModelAttribute ReservationInputForm reservationInputForm,
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,                          
        Model model) 
    {
        Shop shop = shopRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser(); 
        
        // 予約日を取得する
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate reservationDate = LocalDate.parse(reservationInputForm.getReservationDate(), formatter);

        // 予約人数を計算する
        Integer numberOfPeople = reservationInputForm.getNumberOfPeople();      
        
        ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(
            shop.getId(), user.getId(), reservationDate.toString(), reservationInputForm.getNumberOfPeople()
        );
        
        model.addAttribute("shop", shop);  
        model.addAttribute("reservationRegisterForm", reservationRegisterForm);       
        
        return "reservations/confirm";
    }   
    @PostMapping("/shops/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        reservationRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "予約をキャンセルしました。");

        return "redirect:/reservations";
    }  
}