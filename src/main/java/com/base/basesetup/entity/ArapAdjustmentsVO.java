package com.base.basesetup.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arapadjustments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArapAdjustmentsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arapadjustmentsgen")
	@SequenceGenerator(name = "arapadjustmentsgen", sequenceName = "arapadjustmentsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "arapadjustmentsid")
	private Long id;
	@Column(name = "branch",length =25)
	private String branch;
	@Column(name = "finyear",length =20)
	private String finYear;
	@Column(name = "source",length =50)
	private String source;
	@Column(name = "docid",length =50)
	private String docId;
	@Column(name = "refno",length =50)
	private String refNo;
	@Column(name = "accountname",length =50)
	private String accountName;
	@Column(name = "currency",length =20)
	private String currency;
	@Column(name = "acccurrency",length =20)
	private String accCurrency;
	@Column(name = "baseamt",precision = 10,scale =2)
	private float baseAmnt;
	@Column(name = "nativeamt",precision = 10,scale =2)
	private float nativeAmt;
	@Column(name = "offdocid",length =50)
	private String offDocId;
	@Column(name = "vouchertype",length =50)
	private String voucherType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "refdate")
	private LocalDateTime refDate;
	@Column(name = "subledgercode",length =50)
	private String subLedgerCode;
	@Column(name = "exrate",precision = 10,scale =2)
	private float exRate;
	@Column(name = "creditdays",length =10)
	private String creditDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "duedate")
	private LocalDateTime dueDate;
	@Column(name = "orgid",length =20)
	private Long orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "createdby",length =25)
	private String createdBy;
	@Column(name = "modifiedby",length =25)
	private String updatedBy;
	@Column(name = "branchcode",length =20)
	private String branchCode;
	@Column(name = "screencode",length =10)
	private String screenCode="AA";
	@Column(name = "screenname",length =25)
	private String screenName="ARAPAdJUSTMENTS";
	@Column(name = "ipno",length =15)
	private String ipNo;
	@Column(name = "latitude",length =100)
	private String latitude;
	@Column(name = "transid",length =50)
	private String transId;
	@Column(name = "chargeableamt",precision = 10,scale =2)
	private float chargeableAmt;
	@Column(name = "tdsamt",precision = 10,scale =2)
	private float tdsAmt;
	@Column(name ="gstflag")
	private boolean gstFlag;
	@Column(name = "officedocid",length =50)
	private String officeDocId;
	@Column(name = "subledgername",length =50)
	private String subLedgerName;
	


	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
