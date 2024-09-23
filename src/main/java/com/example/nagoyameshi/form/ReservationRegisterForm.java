package com.example.nagoyameshi.form;

import java.time.LocalTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer shopId;
    
    private Integer userId;    
        
    @NotEmpty(message = "予約日は必須です")
    private String reservationDate;   
    
    @NotNull(message = "時間を選択してください。") 
    private LocalTime reservationTime; 
    
    @NotNull(message = "予約人数は必須です")
    private Integer numberOfPeople;
    
}