package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="globalparameter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalParameterVO {
	
	@Id
	@Column(name="userid")
	private Long userId;
	@Column(name="finyear")
	private String finYear;
	@Column(name="branch")
	private String branch;
	@Column(name="branchcode")
	private String branchCode;
	@Column(name="company")
	private String company;

}
