package com.base.basesetup.entity;

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
@Table(name = "costinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costinvoicegen")
	@SequenceGenerator(name = "costinvoicegen", sequenceName = "costinvoiceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "costinvoiceid")
	private Long id;
	@Column(name = "mode")
	private String mode;
	@Column(name = "product")
	private String product;
	@Column(name = "purvchno")
	private String purVchNo;
	@Column(name = "purvchdt")
	private LocalDate purVchDt;
	@Column(name = "costinvoiceno")
	private String costInvoiceNo;
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "supplierbillno")
	private String supplierBillNo;
	@Column(name = "suppliertype")
	private String suppliertType;
	@Column(name = "suppliercode")
	private String supplierCode;
	@Column(name = "creditdays")
	private String creditDays;
	@Column(name = "duedate")
	private LocalDate dueDate;
	@Column(name = "suppliername")
	private String supplierName;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "suppliergstin")
	private String supplierGstIn;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "address")
	private String address;
	@Column(name = "otherinfo")
	private String otherInfo;
	@Column(name = "shipperrefno")
	private String shipperRefNo;
	@Column(name = "gstType")
	private String gstType;
	@Column(name = "payment")
	private String payment;
	@Column(name = "accrualid")
	private String accrualId;
	@Column(name = "utrReference")
	private String utrReference;
	@Column(name = "costtype")
	private String costType;
	@Column(name = "jobstatus")
	private String jobStatus;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;

	@OneToMany(mappedBy = "costInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ChargerCostInvoiceVO> chargerCostInvoiceVO;

	@OneToMany(mappedBy = "costInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TdsCostInvoiceVO> tdsCostInvoiceVO;

	@OneToMany(mappedBy = "costInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<SummaryCostInvoiceVO> summaryCostInvoiceVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
