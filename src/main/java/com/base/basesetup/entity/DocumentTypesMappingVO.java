package com.base.basesetup.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documenttypesmapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypesMappingVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "documenttypesmappinggen")
	@SequenceGenerator(name = "documenttypesmappinggen",sequenceName = "documenttypesmappingseq",initialValue = 1000000001,allocationSize = 1)
	@Column(name="documenttypesmappingid")
	private Long id;
	@Column(name="finyear")
	private String finYear;
	@Column(name="branch")
	private String branch;
	
	@Column(name="active")
    private boolean active;
	@Column(name="createdby")
	private String createdBy;
	@Column(name="modifiedby")
	private String updatedBy;
	@Column(name="orgid")
	private Long orgId;
	@Column(name="cancel")
	private boolean cancel;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	
	@OneToMany(mappedBy = "documentTypesMappingVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DocumentTypesMappingDetailsVO> documentTypesMappingDetailsVO;
	
	@JsonGetter("active")
	public String getActive() {
		return active ? "Active" : "In-Active";
	}
	

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

	
	
}
