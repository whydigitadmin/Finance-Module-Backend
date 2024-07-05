package com.base.basesetup.entity;

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
@Table(name = "summarydebitnote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryDebitNoteVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summarydebitnotegen")
	@SequenceGenerator(name = "summarydebitnotegen", sequenceName = "summarydebitnoteVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "summarydebitnoteid")
	private Long id;
	@Column(name = "billcurrtotchargeamount")
	private String billCurrTotChargeAmount;
	@Column(name = "billcurrtotgrossamount")
	private String billCurrTotGrossAmount;
	@Column(name = "billcurrnetamount")
	private String billCurrNetAmount;
	@Column(name = "amountinwords")
	private String amountInWords;
	@Column(name = "roundoff")
	private String roundOff;
	@Column(name = "lctotchargeamount")
	private String lctotChargeAmount;
	@Column(name = "lctotgrossamount")
	private String lctotGrossAmount;
	@Column(name = "lcnetamount")
	private String lcNetAmount;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "debitnoteid")
	DebitNoteVO debitNoteVO;
	
}
