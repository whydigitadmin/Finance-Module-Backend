package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import com.base.basesetup.entity.ContraVoucherParticularsVO;
import com.base.basesetup.entity.ContraVoucherVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContraVoucherDTO {

	private Long id;
    
	private String currency;
    
	private BigDecimal exRate;

    private String referenceNo;

    private LocalDate referenceDate;
    
    private String chequeNo;

    private LocalDate chequeDate;

    private BigDecimal totalDebitAmount;

    private BigDecimal totalCreditAmount;

    private String remarks;
    
	private String branch;

	private String branchCode;

	private String finYear;

	private String createdBy;

	private Long orgId;
	
	List<ContraVoucherParticularsDTO> contraVoucherParticularsDTO;
}
