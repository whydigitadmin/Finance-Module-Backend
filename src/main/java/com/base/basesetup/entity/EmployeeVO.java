package com.base.basesetup.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeegen")
	@SequenceGenerator(name = "employeegen", sequenceName = "employeeVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "employeeid")
	private Long id;

	@Column(name = "employeecode")
	private String employeeCode;
	@Column(name = "employee")
	private String employeeName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "branch")
	private String branch;
	@Column(name = "branchcode")
	private String branchCode;
	@Column(name = "department")
	private String department;
	@Column(name = "designation")
	private String designation;
	@Column(name = "dateofbirth")
	private String dateOfBirth;
	@Column(name = "joiningdate")
	private LocalDate joiningDate;
	@Column(name = "role")
	private String role;
	@Column(name = "password")
	private String password;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updateBy;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemark;
	@Column(name = "active")
	private boolean active;
	
	
//	@JsonManagedReference
//	@OneToOne(mappedBy = "employeeVO", cascade = CascadeType.ALL)
//	private UserVO userVO;
//	

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
