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
@Table(name = "screen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screengen")
	@SequenceGenerator(name = "screengen", sequenceName = "screenVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "screenid")
	private Long id;
	@Column(name = "screenname")
	private String screenName;
	@Column(name = "responsibilities")
	private String responsibilities;
	
	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "responsibilities_id")
	private ResponsibilitiesVO responsibilitiesVO;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
