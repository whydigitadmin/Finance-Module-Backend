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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "irncreditnote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IrnCreditVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "irncreditgen")
	@SequenceGenerator(name = "irncreditgen", sequenceName = "irncreditseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "irncreditid")
	private Long id;

	// Common fields
	@Column(name = "branch")
	private String branch;

	@Column(name = "branchcode")
	private String branchCode;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "modifiedby")
	private String updatedBy;

	@Column(name = "active")
	private boolean active;

	@Column(name = "cancel")
	private boolean cancel;

	@Column(name = "cancelremarks")
	private String cancelRemarks;

	@Column(name = "finyear")
	private String finYear;

	@Column(name = "screencode")
	@Builder.Default
	private String screenCode="IRN CREDIT NOTE";

	@Column(name = "screenname")
	@Builder.Default
	private String screenName="ICN";

	@Column(name = "ipno")
	private String ipNo;

	@Column(name = "orgid")
	private Long orgId;

	// IRN fields
	@Column(name = "docid")
	private String docId;

	@Column(name = "docdate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate docDate=LocalDate.now();

	@Column(name = "vohno")
	private String vohNo;

	@Column(name = "vohdate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate vohDate;

	@Column(name = "partyname")
	private String partyName;

	@Column(name = "partycode")
	private String partyCode;

	@Column(name = "suprefno")
	private String supRefNo;

	@Column(name = "suprefdate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate supRefDate;

	@Column(name = "partytype")
	private String partyType;

	@Column(name = "product")
	private String product;

	@Column(name = "creditdays")
	private int creditDays;

	@Column(name = "duedate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dueDate;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "officetype")
	private String officeType;

	@Column(name = "currency")
	private String currency;

	@Column(name = "exrate")
	private BigDecimal exRate;

	@Column(name = "status")
	private String status;

	@Column(name = "orginbill")
	private String originBill;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "address")
	private String address;

	@Column(name = "shiprefno")
	private String shipRefNo;

	@Column(name = "pincode")
	private int pincode;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "gsttype")
	private String gstType;

	@Column(name = "billingmonth")
	private String billingMonth;

	@Column(name = "otherinfo")
	private String otherInfo;

	@Column(name = "salestype")
	private String salesType;

	@Column(name = "creditremarks")
	private String creditRemarks;

	@Column(name = "charges")
	private String charges;

	// Summary Fields
	@Column(name = "totchargesbillcurramt")
	private BigDecimal totChargesBillCurrAmt;

	@Column(name = "totchargeslcamt")
	private BigDecimal totChargesLCAmt;

	@Column(name = "totgrossbillamt")
	private BigDecimal totGrossBillAmt;

	@Column(name = "totgrosslcamt")
	private BigDecimal totGrossLCAmt;

	@Column(name = "netbillcurramt")
	private BigDecimal netBillCurrAmt;

	@Column(name = "netlcamt")
	private BigDecimal netLCAmt;

	@Column(name = "amtinwords")
	private String amtInWords;

	@Column(name = "tottaxamt")
	private BigDecimal totTaxAmt;

	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}

	@OneToMany(mappedBy = "irnCreditVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<IrnCreditChargesVO> irnCreditChargesVO;

	@OneToMany(mappedBy = "irnCreditVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<IrnCreditGstVO> irnCreditGstVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
