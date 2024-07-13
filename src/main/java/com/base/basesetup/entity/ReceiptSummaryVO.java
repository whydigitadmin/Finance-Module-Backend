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
@Table(name = "receiptsummary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptSummaryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiptsummarygen")
	@SequenceGenerator(name = "receiptsummarygen", sequenceName = "receiptsummaryseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "receiptsummaryid")
	private Long id;
	@Column(name = "foxengainorloss")
	private BigDecimal foxenGainOrLoss;
	@Column(name = "roundoffamount")
	private BigDecimal roundOffAmount;
	@Column(name = "totalsettled")
	private BigDecimal totalSettled;
	@Column(name = "otheraccnetamt")
	private BigDecimal otherAccNetAmt;
	@Column(name = "onaccount")
	private BigDecimal onAccount;
	@Column(name = "narration")
	private String narration;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "receiptreversalid")
	ReceiptReversalVO receiptReversalVO;
}
