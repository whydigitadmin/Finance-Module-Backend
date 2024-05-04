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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taxmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxMaster2VO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "taxmaster2gen")
	@SequenceGenerator(name = "taxmaster2gen", sequenceName = "taxmaster2seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "settaxrate2id")
	private Long taxMasterId;
	@Column(name = "Serialno",length =50)
	private long SerialNo;
	@Column(name = "fromdate")
	private LocalDate fromDate;
	@Column(name = "todate")
	private LocalDate toDate;
	@Column(name = "tcspercentage",precision =2,scale = 3)
	private float tcsPercentage;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
}
