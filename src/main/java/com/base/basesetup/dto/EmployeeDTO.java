package com.base.basesetup.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private Long id;
	private Long orgId;
	private String employeeCode;
	private String employeeName;
	private String gender;
	private String branch;
	private String branchCode;
	private String department;
	private String designation;
	private String dateOfBirth;
	private LocalDate joiningDate;
	private String password;
	private String role;
	private String userId;
	private String createdBy;
	private boolean active;
}
