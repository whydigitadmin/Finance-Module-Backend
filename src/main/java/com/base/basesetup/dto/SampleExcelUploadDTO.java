package com.base.basesetup.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleExcelUploadDTO {
	private Long id;
	private String name;
	private String email;
	private String address;
	private Long mobile;

}