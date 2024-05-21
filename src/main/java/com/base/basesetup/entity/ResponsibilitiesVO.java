package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsibilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibilitiesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsibilitiesgen")
	@SequenceGenerator(name = "responsibilitiesgen", sequenceName = "responsibilitiesVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "responsibilitiesid")
	private Long id;

	@Column(name = "role")
	private String role;
	@Column(name = "responsibilities")
	private String responsibilities;
	@Column(name = "active")
	private boolean active;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "role_id")
	private RoleVO roleVO;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
