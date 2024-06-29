package com.example.nagoyameshi.form;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopEditForm {
	@NotNull
    private Integer id;
	
	@NotBlank(message = "店舗名を入力してください。")
    private String name;
        
    private MultipartFile imageFile;
    
    @NotBlank(message = "説明を入力してください。")
    private String description;     
        
    @NotNull(message = "開店時間（時）を入力してください。")
	@Range(min = 0, max = 23, message = "開店時間（時）は0から23の間に設定してください。")
	private Integer openingTimeHour;

	@NotNull(message = "開店時間（分）を入力してください。")
	@Range(min = 0, max = 59, message = "開店時間（分）は0から59の間に設定してください。")
	private Integer openingTimeMinute;

	@NotNull(message = "閉店時間（時）を入力してください。")
	@Range(min = 0, max = 23, message = "閉店時間（時）は0から23の間に設定してください。")
	private Integer closingTimeHour;

	@NotNull(message = "閉店時間（分）を入力してください。")
	@Range(min = 0, max = 59, message = "閉店時間（分）は0から59の間に設定してください。")
	private Integer closingTimeMinute;
    
    @NotBlank(message = "定休日を入力してください。")
    private String regularOff;
    
    @NotNull(message = "料金を入力してください。")
    @Min(value = 1, message = "料金は1円以上に設定してください。")
    private Integer price;
    
    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;
    
    @NotBlank(message = "住所を入力してください。")
    private String address;
    
    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;
}


