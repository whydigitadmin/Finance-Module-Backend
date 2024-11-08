package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bankingwithdrawal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankingWithdrawalVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankingwithdrawalgen")
	@SequenceGenerator(name = "bankingwithdrawalgen", sequenceName = "bankingwithdrawalseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "bankingwithdrawalid")
	private Long id;

	@Column(name = "withdrawalmode")
    private String withdrawalMode;

    @Column(name = "docid")
    private String docId;

    @Column(name = "docdate")
    private LocalDate docDate= LocalDate.now();

    @Column(name = "receivedfrom")
    private String receivedFrom;

    @Column(name = "chequeno")
    private String chequeNo;

    @Column(name = "chequedate")
    private LocalDate chequeDate;

    @Column(name = "chequebank")
    private String chequeBank;

    @Column(name = "bankaccount")
    private String bankAccount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "exchangerate", precision = 10, scale = 2)
    private BigDecimal exchangeRate;

    @Column(name = "withdrawalamount", precision = 15, scale = 2)
    private BigDecimal withdrawalAmount;

    @Column(name = "totaldebitamount", precision = 15, scale = 2)
    private BigDecimal totalDebitAmount;

    @Column(name = "totalcreditamount", precision = 15, scale = 2)
    private BigDecimal totalCreditAmount;

    @Column(name = "totalamount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "remarks")
    private String remarks;
    
    

	@Column(name = "branch", length = 25)
	private String branch;

	@Column(name = "branchcode", length = 20)
	private String branchCode;

	@Column(name = "finyear", length = 5)
	private String finYear;

	@Column(name = "createdby", length = 25)
	private String createdBy;

	@Column(name = "modifyby", length = 25)
	private String updatedBy;

	@Column(name = "active")
	private boolean active;

	@Column(name = "cancel")
	private boolean cancel;

	@Column(name = "cancelremarks", length = 50)
	private String cancelRemarks;

	@Column(name = "orgid")
	private Long orgId;
    
	@OneToMany(mappedBy = "bankingWithdrawalVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<WithdrawalParticularsVO> withdrawalParticularsVO;
    
	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}
    
    @Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
