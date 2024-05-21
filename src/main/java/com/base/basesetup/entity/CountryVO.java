package com.base.basesetup.entity;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrygen")
	@SequenceGenerator(name = "countrygen", sequenceName = "countryVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "countryid")
	private Long id;
	@Column(name = "country")
	private String countryName;
	@Column(name = "countrycode")
	private String countryCode;
	@Column(name = "active")
	private boolean active;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "userid")
	private String userId;	
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancel")
	private boolean cancel;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}