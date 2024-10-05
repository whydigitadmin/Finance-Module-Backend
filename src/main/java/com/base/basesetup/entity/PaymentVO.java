package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentgen")
	@SequenceGenerator(name = "paymentgen", sequenceName = "paymentseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "paymentid")
	private Long id;
	
	@Column(name = "branch")
	private String branch;
	@Column(name = "paymenttype")
	private String paymentType;
	@Column(name = "docdate")
	private LocalDate docdate;
	@Column(name = "docid")
	private String docId;
	@Column(name = "bank")
	private String bank;
	@Column(name = "balance")
	private BigDecimal balance;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "refdate")
	private LocalDate refDate;
	@Column(name = "refno")
	private String refNo;
	@Column(name = "reconciled")
	private boolean reconciled;
	@Column(name = "payeetype")
	private String payeeType;
	@Column(name = "payeename")
	private String payeeName;
	@Column(name = "modeofpayment")
	private String modeOfPayment;
	@Column(name = "chqbook")
	private String chqBook;
	@Column(name = "chqcardno")
	private String chqCardNo;
	@Column(name = "chqdddt")
	private String chqDdDt;
	@Column(name = "netamount")
	private String netAmount;
	@Column(name = "remarks")
	private String remarks;

	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "canelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	
	@OneToMany(mappedBy = "paymentVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<AccountParticularsVO> accountParticularsVO;
	

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
