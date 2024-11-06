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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmsjobcard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TmsJobCardVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tmsjobcardgen")
	@SequenceGenerator(name = "tmsjobcardgen", sequenceName = "tmsjobcardseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "tmsjobcardid")
	private Long id;
	@Column(name = "jobno")
	private String jobNo;
	@Column(name = "customer")
	private String customer;
	@Column(name = "operationclosed")
	private boolean operationClosed;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "salescategory")
	private String salesCategory;
	@Column(name = "financeclosed")
	private boolean financeClosed;
	@Column(name = "salesperson")
	private String salesPerson;
	@Column(name = "closed")
	private boolean closed;
	@Column(name = "closedon")
	private String closedOn;
	@Column(name = "income")
	private BigDecimal income;
	@Column(name = "expense")
	private BigDecimal expense;
	@Column(name = "profit")
	private BigDecimal profit;
	@Column(name = "remarks")
	private String remarks;

	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "branch", length = 25)
	private String branch;
	@Column(name = "branchcode", length = 20)
	private String branchCode;
	@Column(name = "createdby", length = 25)
	private String createdBy;
	@Column(name = "modifiedby", length = 25)
	private String updatedBy;
	@Column(name = "active")
	private boolean active;

	@OneToMany(mappedBy = "tmsJobCardVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<CostCenterTmsJobCardVO> costCenterTmsJobCardVO;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
