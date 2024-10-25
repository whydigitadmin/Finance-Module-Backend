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
@Table(name = "receipt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiptgen")
	@SequenceGenerator(name = "receiptgen", sequenceName = "receiptseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "receiptid")
	private Long id;

	// Receipt fields
	@Column(name = "docid")
	private String docId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "docdate")
	@Builder.Default
	private LocalDate docDate=LocalDate.now();
	@Column(name = "type")
	private String type;
	@Column(name = "customername")
	private String customerName;
	@Column(name = "customercode")
	private String customerCode;
	@Column(name = "bankcashacc")
	private String bankCashAcc;
	@Column(name = "receiptamt")
	private BigDecimal receiptAmt;
	@Column(name = "taxamt")
	private BigDecimal taxAmt;
	@Column(name = "bankchargeacc")
	private String bankChargeAcc;
	@Column(name = "bankcharges")
	private BigDecimal bankCharges;
	@Column(name = "incurrencybnkchargs")
	private String inCurrencyBnkChargs;
	@Column(name = "tdsamt")
	private BigDecimal tdsAmt;
	@Column(name = "incurrencytdsamt")
	private String inCurrencyTdsAmt;
	@Column(name = "chequebank", length = 20)
	private String chequeBank;
	@Column(name = "receipttype", length = 20)
	private String receiptType;
	@Column(name = "chequeutino", length = 10)
	private String chequeUtiNo;
	@Column(name = "chequeutidt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate chequeUtiDt;
	@Column(name = "receivedfrom")
	private String receivedFrom;
	@Column(name = "netamount")
	private String netAmount;
	@Column(name = "remarks")
	private String remarks;
	

	// Common Fields
	@Column(name = "branch")
	private String branch;
	@Column(name = "branchcode")
	private String branchCode;
	@Column(name = "customer")
	private String customer;
	@Column(name = "client")
	private String client;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifyedby")
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
	private String screenCode;
	@Column(name = "screenname")
	private String screenName;
	@Column(name = "ipno")
	private String ipNo;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "receiptType1")
	private String receiptType1;
	@Column(name = "currency")
	private String currency;
	@Column(name = "currentamount")
	private String currencyAmount;
	@Column(name = "orgid")
	private Long orgId;
	
	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}

	@OneToMany(mappedBy = "receiptVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ReceiptInvDetailsVO> receiptInvDetailsVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
