package com.base.basesetup.entity;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "particularsreconcile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticularsReconcileVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particularsreconcilegen")
	@SequenceGenerator(name = "particularsreconcilegen", sequenceName = "particularsreconcileseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "particularsreconcileid")
	private Long id;
	@Column(name="voucherno")
	private String voucherNo;
	@Column(name="voucherdate")
	private LocalDate voucherDate;
	@Column(name="chequeno")
	private String chequeNo;
	@Column(name="chequedate")
	private LocalDate chequeDate;
	@Column(name="deposit")
	private BigDecimal deposit;
	@Column(name="withdrawal")
	private BigDecimal withdrawal;
	@Column(name="bankref")
	private String bankRef;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="reconcilebankid")
	ReconcileBankVO reconcileBankVO;
	
	

}
