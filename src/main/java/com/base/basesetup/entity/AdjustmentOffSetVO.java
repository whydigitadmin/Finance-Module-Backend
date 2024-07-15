//package com.base.basesetup.entity;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "receiptinvoice")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class ReceiptInvoiceVO {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiptinvoicegen")
//	@SequenceGenerator(name = "receiptinvoicegen", sequenceName = "receiptinvoiceseq", initialValue = 1000000001, allocationSize = 1)
//	@Column(name = "receiptinvoiceid")
//	private Long id;
//}
