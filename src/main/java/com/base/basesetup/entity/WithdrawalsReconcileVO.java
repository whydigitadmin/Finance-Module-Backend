package com.base.basesetup.entity;



import java.math.BigDecimal;
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
@Table(name = "withdrawalsreconcile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalsReconcileVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "withdrawalsreconcilegen")
	@SequenceGenerator(name = "withdrawalsreconcilegen", sequenceName = "withdrawalsreconcileseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "withdrawalsreconcileid")
	private Long id;
	@Column(name="voucherno")
	private String voucherNo;
	@Column(name="voucherdate")
	private LocalDateTime voucherDate;
	@Column(name="chqddno")
	private String chqDdNo;
	@Column(name="chqdddate")
	private LocalDateTime chqDdDate;
	@Column(name = "cleareddate")
	private LocalDateTime clearedDate;
	@Column(name="paymentamt")
	private BigDecimal paymentAmt;
	@Column(name="paymentname")
	private String paymentName;
	@Column(name="narration")
	private String narration;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "reconcileid")
	ReconcileVO reconcileVO;
	
	
	
}
