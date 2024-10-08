package com.example.nagoyameshi.form;

import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約日を選択してください。")
    private String reservationDate;   
	
	@NotNull(message = "時間を選択してください。") 
    private LocalTime reservationTime; 
    
    @NotNull(message = "予約人数を入力してください。")
    @Min(value = 1, message = "予約人数は1人以上に設定してください。")
    private Integer numberOfPeople;
    }