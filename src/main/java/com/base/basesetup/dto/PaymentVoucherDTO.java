package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherDTO {
	private Long id;
	private String vehicleSubType;
	private String referenceNo;
	private String currency;
	private String docid;
	private LocalDateTime referenceDate;
	private BigDecimal exRate;
	private LocalDateTime docDate;
	private String remarks;

	List<SummaryPaymentVoucherDTO> summaryPaymentVoucherDTO;

	List<ParticularsPaymentVoucherDTO> particularsPaymentVoucherDTO;
}
