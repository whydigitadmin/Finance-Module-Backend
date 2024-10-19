package com.base.basesetup.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.base.basesetup.entity.PaymentVoucherVO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSummaryDTO {

	private Long id;
	@Column(name ="totaldebitamount",precision =10,scale =2)
	private float totalDebitAmount;
	@Column(name ="totalcreditamount",precision =10,scale =2)
	private float totalCreditAmount;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "paymentvoucherid")
	PaymentVoucherVO paymentVoucherVO;
	
}
