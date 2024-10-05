package com.base.basesetup.entity;

import java.time.LocalDateTime;

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
@Table(name = "particularsaccountreceipt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsAccountReceiptVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particularsaccountreceiptgen")
	@SequenceGenerator(name = "particularsaccountreceiptgen", sequenceName = "particularsaccountreceiptseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "particularsaccountreceiptid")
	private Long id;
	@Column(name="fromdate")
	private LocalDateTime fromDate;
	@Column(name="todate")
	private LocalDateTime toDate;
	@Column(name="tcs")
    private Long tcs;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="receiptreceivableid")
	ReceiptReceivableVO receiptReceivableVO;
	
}
