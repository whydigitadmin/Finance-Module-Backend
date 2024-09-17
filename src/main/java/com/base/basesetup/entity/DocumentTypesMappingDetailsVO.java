package com.base.basesetup.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documenttypesmappingdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentTypesMappingDetailsVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "documenttypesmappingdetailsgen")
	@SequenceGenerator(name = "documenttypesmappingdetailsgen",sequenceName = "documenttypesmappingdetailsseq",initialValue = 1000000001,allocationSize = 1)
	@Column(name="documenttypesmappingdetailsid")
	private Long id;
	@Column(name="screenname")
	private String screenName;
	@Column(name="screencode")
	private String screenCode;
	@Column(name="finyear")
	private String finYear;
	@Column(name="branch")
	private String branch;
	@Column(name="branchcode")
	private String branchCode;
	@Column(name="finyearid")
	private String finyearId;
	@Column(name="doccode")
	private String docCode;
	@Column(name="prefix")
	private String prefix;
	@Column(name="lastno")
	private String lastNo;
	@Column(name="orgid")
	private Long orgId;
	
	@ManyToOne
	@JoinColumn(name = "documenttypesmappingid")
	@JsonBackReference
	private DocumentTypesMappingVO documentTypesMappingVO;
	
	
	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
