package com.base.basesetup.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "summarycostinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryCostInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summarycostinvoicegen")
	@SequenceGenerator(name = "summarycostinvoicegen", sequenceName = "summarycostinvoiceVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summarycostinvoiceid")
	private Long id;
	@Column(name = "billcurrtotchargeamt")
	private BigDecimal billCurrTotChargeAmt;
	@Column(name = "billcurractbillamt")
	private BigDecimal billCurrActBillAmt;
	@Column(name = "billcurrnetamt")
	private BigDecimal billCurrNetAmt;
	@Column(name = "lctotchargeamt")
	private BigDecimal lcTotChargeAmt;
	@Column(name = "lcactbillamt")
	private BigDecimal lcActBillAmt;
	@Column(name = "lcnetamt")
	private BigDecimal lcNetAmt;
	@Column(name = "roundoff")
	private String roundOff;
	@Column(name = "lcgstinputamt")
	private BigDecimal lcGstInputAmt;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "costinvoice_id")
	CostInvoiceVO costInvoiceVO;

}
