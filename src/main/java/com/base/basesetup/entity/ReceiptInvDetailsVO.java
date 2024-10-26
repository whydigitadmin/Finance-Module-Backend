package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receiptinvdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptInvDetailsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiptinvdetailsgen")
	@SequenceGenerator(name = "receiptinvdetailsgen", sequenceName = "receiptinvdetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "receiptinvdetailsid")
	private Long id;

	@Column(name = "invno")
	private String invNo;

	@Column(name = "invdate")
	private LocalDate invDate;

	@Column(name = "refno")
	private String refNo;

	@Column(name = "refdate")
	private LocalDate refDate;

	@Column(name = "masterref")
	private String masterRef;

	@Column(name = "houseref")
	private String houseRef;

	@Column(name = "currency")
	private String currency;

	@Column(name = "exrate")
	private BigDecimal exRate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "chargeamt")
	private BigDecimal chargeAmt;

	@Column(name = "outstanding")
	private BigDecimal outstanding;

	@Column(name = "settled")
	private BigDecimal settled;

	@Column(name = "recexrate")
	private BigDecimal recExRate;

	@Column(name = "txnsettled")
	private BigDecimal txnSettled;

	@Column(name = "gainamt")
	private BigDecimal gainAmt;


	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "receiptid")
	ReceiptVO receiptVO;

}
