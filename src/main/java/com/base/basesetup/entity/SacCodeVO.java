package com.base.basesetup.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.base.basesetup.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saccode")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SacCodeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saccodegen")
	@SequenceGenerator(name = "saccodegen", sequenceName = "saccodeseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "saccodeid")
	private Long id;
	@Column(name = "orgid")
	private Long orgId;
	@Column(name = "serviceaccountcode")
	private String serviceAccountCode;
	@Column(name = "sacdescription")
	private String sacDescription;
	@Column(name = "chapter")
	private String chapter;
	@Column(name = "product")
	private String product;
	
	@Builder.Default
	@Column(name = "screencode")
	private String screenCode="HSC";
	@Builder.Default
	@Column(name="screenname")
	private String screenName="HSN SAC CODE";
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancelremarks")
	private String cancelRemarks;
	@Column(name = "cancel")
	private boolean cancel;
	@Column(name = "active")
	private boolean active;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
