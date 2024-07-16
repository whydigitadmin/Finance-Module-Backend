package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxInvoiceDTO {
	private Long id;
	private String headerColumns;
	private String partyName;
	private String partyCode;
	private String partyType;
	private String addressType;
	private String recipientGSTIN;
	private String placeOfSupply;
	private String address;
	private String pincode;
	private String status;
	private String GSTType;
	private LocalDate dueDate;
	private String billCurr;
	private String salesType;
	private Long orgId;
	private boolean active;
	private String updatedBy;
	private String createdBy;
	private LocalDateTime docDate;
	private LocalDateTime invoiceDate;
	private BigDecimal lcChargeAmount;
	private BigDecimal lcTaxAmount;
	private BigDecimal lcInvAmount;
	private BigDecimal lcRoundOffAmount;
	private BigDecimal billlcChargeAmount;
	private BigDecimal billTaxAmount;
	private BigDecimal billInvAmount;
	private BigDecimal lcTaxableAmount;
	private String amountInwords;
	private String billingRemarks;
	
	List<ChargerTaxInvoiceDTO> chargerTaxInvoiceDTO;

	List<GstTaxInvoiceDTO> gstTaxInvoiceDTO;
}