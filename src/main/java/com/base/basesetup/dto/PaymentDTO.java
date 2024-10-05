package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

	private Long id;
	private String branch;
	private String paymentType;
	private LocalDate docdate;
	private String docId;
	private String bank;
	private BigDecimal balance;
	private String currency;
	private String exRate;
	private LocalDate refDate;
	private String refNo;
	private boolean reconciled;
	private String payeeType;
	private String payeeName;
	private String modeOfPayment;
	private String chqBook;
	private String chqCardNo;
	private String chqDdDt;
	private String netAmount;
	private String remarks;

	private Long orgId;
	private boolean active;
	private boolean cancel;
	private String cancelRemarks;
	private String createdBy;
	
	private List<AccountParticularsDTO> accountParticularsDTO;

}
