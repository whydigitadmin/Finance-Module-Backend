package com.base.basesetup.entity;

import java.time.LocalDate;
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
@Table(name = "paymentvoucher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentVoucherVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentvouchergen")
	@SequenceGenerator(name = "paymentvouchergen", sequenceName = "paymentvoucherseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "paymentvoucherid")
	private Long id;
	@Column(name = "vehiclesubtype",length=30)
	private String vehicleSubType;
	@Column(name = "referenceno")
	private String referenceNo;
	@Column(name = "docId")
	private String docid;
	@Column(name = "referencedate")
	private LocalDate referenceDate;
	@Column(name = "docdate")
	private LocalDate docDate=LocalDate.now();;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "finyear")
	private String finyear;
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
	@Column(name = "chequeno")
	private String chequeNo;
	@Column(name = "chequedate")
	private String chequeDate;
	@Column(name = "chequebank")
	private String chequeBank;
	@Column(name = "branch")
	private String branch;
	@Column(name = "branchcode")
	private String branchCode;
	@Column(name = "screencode")
	private String screenCode;
	@Column(name = "screenname")
	private String screenName;
	@Column(name = "currency")
	private String currency;

	@OneToMany(mappedBy = "paymentVoucherVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsPaymentVoucherVO> particularsPaymentVoucherVO;
	
	@OneToMany(mappedBy = "paymentVoucherVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<PaymentSummaryVO> paymentSummaryVO;
	
	
	

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

	@PrePersist
	private void setDefaultFinyr() {
		// Execute the logic to set the default value for finyr
		String fyFull = calculateFinyr();
		this.finyear = fyFull; 
	}

	private String calculateFinyr() {
		// Logic to calculate finyr based on the provided SQL query
		String currentMonthDay = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
		String fyFull = (currentMonthDay.compareTo("0331") > 0)
				? LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))
				: LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy"));
		return fyFull;
	}
}
