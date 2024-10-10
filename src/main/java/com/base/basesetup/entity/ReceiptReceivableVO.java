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
	@Column(name = "branch")
	private String branch;
	@Column(name = "receipttype")
	private String receiptType;
	@Column(name = "docdate")
	private LocalDate docDate;
	@Column(name = "docid")
	private String docId;
	@Column(name = "modeofpayment")
	private String modeOfPayment;
	@Column(name = "bankcashac")
	private String bankCashAc;
	@Column(name="currency")
	private String currency;
	@Column(name = "exrates")
	private BigDecimal exRates;
	@Column(name = "balance")
	private String balance;
	@Column(name = "receivedfrom")
	private String receivedFrom;
	@Column(name="cheqddcardbank")
	private String cheqDdCardBank;
	@Column(name="cheqddcardno")
	private String cheqDdCardNo;
	@Column(name="cheqdddate")
	private LocalDate cheqDdDate;
	@Column(name="reconciled")
	private boolean reconciled;
	
	@Builder.Default
	@Column(name = "screencode")
	private String screenCode="RT";
	@Builder.Default
	@Column(name="screenname")
	private String screenName="RECEIPT";
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
	
	
	@Column(name="netamount")
	private BigDecimal netAmount;
	@Column(name="remarks")
	private String remarks;
	
	@OneToMany(mappedBy = "receiptReceivableVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsAccountReceiptVO>particularsAccountReceiptVO;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
