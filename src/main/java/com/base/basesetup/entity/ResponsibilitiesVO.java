package com.base.basesetup.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@SequenceGenerator(name = "responsibilitiesgen", sequenceName = "responsibilitiesseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "responsibilitiesid")
	private Long id;
	@Column(name = "role")
	private String role;
	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	
	@OneToMany(mappedBy = "responsibilitiesVO", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ScreenVO> screenVO;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
