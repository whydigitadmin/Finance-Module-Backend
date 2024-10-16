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
	@Column(name = "vouchersubtype")
	private String voucherSubType;
	@Column(name = "docdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate docDate;
	@Column(name = "docid")
	private String docId;
	
	@Column(name = "currency")
	private String currency;
	@Column(name = "exrate")
	private String exRate;
	@Column(name = "refno")
	private String refNo;
	@Column(name = "refdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate refDate;
	
    @Column(name = "screencode", length = 5)
    private String screenCode= "GJ";

    @Column(name = "screenname", length = 25)
    private String screenName = "GENERALJOURNAL";

	@Column(name = "remarks")
	private String remarks;
	@Column(name = "orgid")
	private Long orgId;
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
