package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInvDtlsDTO {

	private Long id;

	private String invNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate invDate;

	private String refNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate refDate;

	private String supplierRefNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate supplierRefDate;

	private String currency;

	private BigDecimal exRate;

	private BigDecimal amount;

	private BigDecimal chargeAmt;

	private BigDecimal outstanding;

	private BigDecimal settled;

	private BigDecimal payExRate;

	private BigDecimal txnSettled;

	private BigDecimal gainOrLossAmt;

	private String remarks;
	
	private LocalDate fromDate;

	private LocalDate toDate;

}
