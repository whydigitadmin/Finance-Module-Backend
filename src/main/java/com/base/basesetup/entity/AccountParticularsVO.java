package com.base.basesetup.entity;

import java.math.BigDecimal;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accountparticulars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountParticularsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "accountparticularsgen")
	@SequenceGenerator(name = "accountparticularsgen", sequenceName = "accountparticularsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "accountparticularsid")
	private Long id;
	
	@Column(name = "accountname")
	private String accountName;
	@Column(name = "subledger")
	private String subledger;
	@Column(name = "dbamount")
	private BigDecimal dbAmount;
	@Column(name = "cramount")
	private BigDecimal crAmount;
	@Column(name = "narration")
	private String narration;
	
	@ManyToOne
	@JoinColumn(name = "paymentid")
	@JsonBackReference
	private PaymentVO paymentVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
