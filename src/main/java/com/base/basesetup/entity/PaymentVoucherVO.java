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
	@Column(name = "vehiclesubtype")
	private String vehicleSubType;
	@Column(name = "referenceno")
	private String referenceNo;
	@Column(name = "currency")
	private String currency;
	@Column(name = "docId")
	private String docid;
	@Column(name = "referencedate")
	private LocalDateTime referenceDate;
	@Column(name = "exrate")
	private BigDecimal exRate;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "remarks")
	private String remarks;
	private String finyr;
	@Column(name = "orgid")
	private String orgId;
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
	@Column(name = "totaldebitamount")
	private BigDecimal totalDebitAmount;
	@Column(name = "totalcreditamount")
	private BigDecimal totalCreditAmount;

	@OneToMany(mappedBy = "paymentVoucherVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsPaymentVoucherVO> particularsPaymentVoucherVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

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
}
