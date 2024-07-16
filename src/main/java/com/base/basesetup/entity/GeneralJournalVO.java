package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "generaljournal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralJournalVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generaljournalgen")
	@SequenceGenerator(name = "generaljournalgen", sequenceName = "generaljournalseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "generaljournalid")
	private Long id;
	@Column(name = "branch")
	private String branch;
	@Column(name = "vouchertype")
	private String voucherType;
	@Column(name = "docdate")
	private LocalDateTime docDate;
	@Column(name = "docid")
	private String docId;
	@Column(name = "template")
	private String template;
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "refno")
	private String refNo;
	@Column(name = "refdate")
	private LocalDateTime refDate;
	@Column(name = "reverseon")
	private LocalDateTime reverseOn;
	@Column(name = "narration")
	private String narration;
	@Column(name = "orgid")
	private String orgId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "totaldebitamount")
	private BigDecimal totalDebitAmount;
	@Column(name = "totalcreditamount")
	private BigDecimal totalCreditAmount;

	@OneToMany(mappedBy = "generalJournalVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<ParticularsJournalVO> particularsJournalVO;
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
