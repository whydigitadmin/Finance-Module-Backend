package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
	@SequenceGenerator(name = "taxinvoicegen", sequenceName = "taxinvoiceVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "taxinvoiceid")
	private Long id;
	@Column(name = "headercolumns")
	private String headerColumns;
	@Column(name = "partyname")
	private String partyName;
	@Column(name = "partycode")
	private String partyCode;
	@Column(name = "partytype")
	private String partyType;
	@Column(name = "addresstype")
	private String addressType;
	@Column(name = "recipientgstin")
	private String recipientGSTIN;
	@Column(name = "placeofsupply")
	private String placeOfSupply;
	@Column(name = "address")
	private String address;
	@Column(name = "pincode")
	private String pincode;
	@Column(name = "status")
	private String status;
	@Column(name = "gsttype")
	private String GSTType;
	@Column(name = "duedate")
	private LocalDate dueDate;
	@Column(name = "billcurr")
	private String billCurr;
	@Column(name = "salestype")
	private String salesType;
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
	@Column(name = "docid")
	private String docId;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "invoiceno")
	private String invoiceNo;
	@Column(name = "invoicedate")
	private LocalDateTime invoiceDate;
	@Column(name = "finyr")
	private String finyr;
	
	@Column(name = "lcchargeamount")
	private BigDecimal lcChargeAmount;
	@Column(name = "lctaxamount")
	private BigDecimal lcTaxAmount;
	@Column(name = "lcinvamount")
	private BigDecimal lcInvAmount;
	@Column(name = "lcroundoffamount")
	private BigDecimal lcRoundOffAmount;
	@Column(name = "billchargeamount")
	private BigDecimal billlcChargeAmount;
	@Column(name = "billtaxamount")
	private BigDecimal billTaxAmount;
	@Column(name = "billinvamount")
	private BigDecimal billInvAmount;
	@Column(name = "lctaxableamount")
	private BigDecimal lcTaxableAmount;
	@Column(name = "amountinwords")
	private String amountInwords;
	@Column(name = "billingremarks")
	private String billingRemarks;

	@PrePersist
	private void setDefaultFinyr() {
		// Execute the logic to set the default value for finyr
		String fyFull = calculateFinyr();
		this.finyr = fyFull;
	}

	private String calculateFinyr() {
		// Logic to calculate finyr based on the provided SQL query
		String currentMonthDay = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
		String fyFull = (currentMonthDay.compareTo("0331") > 0)
				? LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))
				: LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy"));
		return fyFull;
	}

	@OneToMany(mappedBy = "taxInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ChargerTaxInvoiceVO> chargerTaxInvoiceVO;
	

	@OneToMany(mappedBy = "taxInvoiceVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<GstTaxInvoiceVO> gstTaxInvoiceVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}