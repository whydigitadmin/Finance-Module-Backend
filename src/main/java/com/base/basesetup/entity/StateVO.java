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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "state")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stategen")
	@SequenceGenerator(name = "stategen",sequenceName = "stateVO",initialValue = 1000000001,allocationSize = 1)
	@Column(name="stateid")
	private Long id;
	
	@Column(name="statecode", length = 30)
	private String stateCode;
	@Column(name="state", length = 30)
	private String stateName;
	@Column(name="country", length = 30)
    private String country;
	@Column(name="region", length = 30)
    private String region;
	@Column(name="stateno", length = 30)
    private int stateNumber;
	@Column(name="active")
    private boolean active;
	@Column(name="userid", length = 30)
    private String userId;
    @Column(unique = true)
	private String dupchk;
    @Column(name="createdby", length = 30)
    private String createdBy;
    @Column(name="modifiedby", length = 30)
	private String updatedBy;
    @Column(name="orgid", length = 30)
	private Long orgId;
    @Column(name="cancel")
	private boolean cancel;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
