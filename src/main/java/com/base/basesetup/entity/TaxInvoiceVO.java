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
@Table(name = "taxinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxinvoicegen")
	@SequenceGenerator(name = "taxinvoicegen", sequenceName = "taxinvoiceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "taxinvoiceid")
	private Long id;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "branch")
	private String branch;
	@Column(name = "branchcode")
	private String branchCode;
	@Column(name = "finyear")
	private String finYear;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String modifiedBy;
	@Column(name = "active")
	private boolean active=true;
	@Column(name = "cancel")
	private boolean cancel=false;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "biztype")
	private String bizType;
	@Column(name = "bizmode")
	private String bizMode;
	@Column(name = "partyname")
	private String partyName;
	@Column(name = "partycode")
	private String partyCode;
	@Column(name = "partytype")
	private String partyType;
	@Column(name = "stateno")
	private String stateNo;
	@Column(name = "statecode")
	private String stateCode;
	@Column(name = "recipientgstin")
	private String recipientGSTIN;
	@Column(name = "placeofsupply")
	private String placeOfSupply;
	@Column(name = "addresstype")
	private String addressType;
	@Column(name = "address")
	private String address;
	@Column(name = "pincode")
	private String pinCode;
	@Column(name = "status")
	private String status;
	@Column(name = "gsttype")
	private String gstType;
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDate docDate= LocalDate.now();
	@Column(name = "supplierbillno")
	private String supplierBillNo;
	@Column(name = "supplierbilldate")
	private LocalDate supplierBillDate;
	@Column(name = "billcurr")
	private String billCurr;
	@Column(name = "billcurrrate")
	private BigDecimal billCurrRate;
	@Column(name = "examount")
	private BigDecimal exAmount;
	@Column(name = "creditdays")
	private int creditDays;
	@Column(name = "contactperson")
	private String contactPerson;
	@Column(name = "shipperinvoiceno")
	private String shipperInvoiceNo;
	@Column(name = "billofentry")
	private String billOfEntry;
	@Column(name = "billmonth")
	private String billMonth;
	
	
	@Column(name = "invoiceno")
	private String invoiceNo;
	@Column(name = "invoicedate")
	private LocalDate invoiceDate;
	@Column(name = "salestype")
	private String salesType;

	@Column(name = "totalchargeamountlc")
	private BigDecimal totalChargeAmountLc;
	@Column(name = "totaltaxamountlc")
	private BigDecimal totalTaxAmountLc;
	@Column(name = "totalinvamountlc")
	private BigDecimal totalInvAmountLc;
	@Column(name = "roundoffamountlc")
	private BigDecimal roundOffAmountLc;
	@Column(name = "totalchargeamountbc")
	private BigDecimal totalChargeAmountBc;
	@Column(name = "totaltaxamountbc")
	private BigDecimal totalTaxAmountBc;
	@Column(name = "totalinvamountbc")
	private BigDecimal totalInvAmountBc;
	@Column(name = "totaltaxableamountlc")
	private BigDecimal totalTaxableAmountLc;
	@Column(name = "amountinwords")
	private String amountInWords;
	@Column(name = "billingremarks")
	private String billingRemarks;

	


	@OneToMany(mappedBy = "taxInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TaxInvoiceDetailsVO> taxInvoiceDetailsVO;

	
	@OneToMany(mappedBy = "taxInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<TaxInvoiceGstVO> taxInvoiceGstVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}