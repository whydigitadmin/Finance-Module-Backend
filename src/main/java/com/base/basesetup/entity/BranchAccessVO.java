package com.base.basesetup.entity;

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
@Table(name = "branchaccess")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BranchAccessVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branchaccessgen")
	@SequenceGenerator(name = "branchaccessgen", sequenceName = "branchaccessseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "branchaccessid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	
	
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonBackReference
	private UserVO userVO;
	

	
	
}