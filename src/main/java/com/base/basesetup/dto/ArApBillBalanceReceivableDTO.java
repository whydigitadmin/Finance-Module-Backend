package com.base.basesetup.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArApBillBalanceReceivableDTO {

private Long id;
private String branch;
private String partyName;
private String partyCode;
private String currency;
private String creditDays;
private String billNo;
private LocalDate billDate;
private String billExRate;
private String supplierRefNo;
private LocalDate supplierRefDate;
private LocalDate dueDate;
private BigDecimal debitAmount;
private BigDecimal creditAmount;
private Long orgId;
private boolean active;
private String createdBy;
private String updatedBy;
}
