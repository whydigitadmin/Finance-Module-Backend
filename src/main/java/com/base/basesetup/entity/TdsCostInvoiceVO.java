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
@Table(name = "tdscostinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TdsCostInvoiceVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tdscostinvoicegen")
	@SequenceGenerator(name = "tdscostinvoicegen", sequenceName = "tdscostinvoiceVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "tdscostinvoiceid")
	private Long id;
	@Column(name = "tdswh")
	private String tdsWh;
	@Column(name = "tdsWpercent")
	private BigDecimal tdsWhPercent;
	@Column(name = "section")
	private String section;
	@Column(name = "totaltds")
	private String totalTds;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "costinvoice_id")
	CostInvoiceVO costInvoiceVO;
}
