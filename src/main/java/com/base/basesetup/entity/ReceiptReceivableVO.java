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
@Table(name = "receiptreceivable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptReceivableVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiptreceivablegen")
	@SequenceGenerator(name = "receiptreceivablegen", sequenceName = "receiptreceivableseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "receiptreceivableid")
	private Long id;

	// Receipt fields
	@Column(name = "docid", length = 50)
	private String docId;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name = "type", length = 50)
	private String type;
	@Column(name = "customername", length = 150)
	private String customerName;
	@Column(name = "customercode", length = 50)
	private String customerCode;
	@Column(name = "bankcashacc", length = 50)
	private String bankCashAcc;
	@Column(name = "receiptamt", precision = 10, scale = 2)
	private BigDecimal receiptAmt;
	@Column(name = "bankchargeacc", length = 50)
	private String bankChargeAcc;
	@Column(name = "bankcharges", precision = 10, scale = 2)
	private BigDecimal bankCharges;
	@Column(name = "incurrencybnkchargs", length = 5)
	private String inCurrencyBnkChargs;
	@Column(name = "tdsamt", precision = 10, scale = 2)
	private BigDecimal tdsAmt;
	@Column(name = "incurrencytdsamt", length = 5)
	private String inCurrencyTdsAmt;
	@Column(name = "chequebank", length = 20)
	private String chequeBank;
	@Column(name = "receipttype", length = 20)
	private String receiptType;
	@Column(name = "chequeutino", length = 10)
	private String chequeUtiNo;
	@Column(name = "chequeutidt")
	private LocalDate chequeUtiDt;
	@Column(name = "receivedfrom", length = 100)
	private String receivedFrom;

	// Common Fields
	@Column(name = "branch", length = 25)
	private String branch;
	@Column(name = "chcode", length = 5)
	private String branchCode;
	@Column(name = "customer", length = 100)
	private String customer;
	@Column(name = "client", length = 30)
	private String client;
	@Column(name = "createdby", length = 25)
	private String createdBy;
	@Column(name = "modifyedby", length = 25)
	private String updatedBy;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks", length = 25)
	private String cancelRemarks;
	@Column(name = "finyear", length = 5)
	private String finYear;
	@Column(name = "screencode", length = 5)
	private String screenCode;
	@Column(name = "screenname", length = 25)
	private String screenName;
	@Column(name = "ipno", length = 15)
	private String ipNo;
	@Column(name = "latitude", length = 100)
	private String latitude;
	@Column(name = "receiptType1", length = 100)
	private String receiptType1;
	@Column(name = "currency", length = 100)
	private String currency;
	@Column(name = "currentamount", length = 100)
	private String currencyAmount;

	@OneToMany(mappedBy = "receiptReceivableVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ReceiptInvDetailsVO> receiptInvDetailsVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
