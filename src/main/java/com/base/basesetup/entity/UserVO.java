
package com.base.basesetup.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Long userId;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="usertype")
	private String userType;
	@Column(name="allindiaaccess")
	private boolean allIndiaAccess;
	@Column(name="employeecode")
	private String employeeCode;
	@Column(name="employeename")
	private String employeeName;
	@Column(name="email")
	private String email;
	@Column(name="reportingto")
    private String reportingTO;
	@Column(name="location")
	private String location;
	@Column(name="active")
	private boolean isActive;
	@Column(name="deactivatedon")
	private LocalDate deactivatedOn;
	@Column(name="orgid")
	private Long orgId;
	
	@Column(name="loginstatus")
	private boolean loginStatus;
	@Column(name="accountremoveddate")
	private Date accountRemovedDate;
	@Column(name="lastlogin")
	private String lastLogin;

	@OneToMany(mappedBy = "userVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserRolesVO> userRoleVO;


	@OneToMany(mappedBy = "userVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BranchAccessVO> branchAccessVO;


	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
