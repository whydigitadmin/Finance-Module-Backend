package com.base.basesetup.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userrole")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userrolegen")
	@SequenceGenerator(name = "userrolegen", sequenceName = "userroleseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "userroleid")
	private Long id;
	@Column(name = "role")
	private String role;
	@Column(name = "startdate")
	private LocalDate startdate;
	@Column(name = "enddate")
	private LocalDate enddate;

	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonBackReference
	private UserVO userVO;



}