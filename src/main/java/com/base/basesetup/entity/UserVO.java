package com.base.basesetup.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.base.basesetup.dto.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersgen")
	@SequenceGenerator(name = "usersgen", sequenceName = "usersseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "userid")
	private Long userId;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "employeename")
	private String employeeName;
	private String email;
	@Column(name = "username")
	private String userName;
	private String password;
	@Column(name = "loginstatus")
	private boolean loginStatus=false;

	private boolean isActive;

	// @Enumerated(EnumType.STRING)
	// private Gender gender;
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
	// private LocalDate dob;
	@Enumerated(EnumType.STRING)
	private Role role;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

	private Date accountRemovedDate;
	
	@JsonBackReference
	@OneToOne
//	@MapsId
	@JoinColumn(name="employeeid")
	private EmployeeVO employeeVO;
}
