package com.base.basesetup.entity;

import java.time.LocalDate;

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
@Table(name = "tdsmaster2")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TdsMaster2VO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tdsmaster2gen")
	@SequenceGenerator(name = "tdsmaster2gen", sequenceName = "tdsmaster2seq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "tdsmaster2id")
	private Long tdsMaster2Id;
	@Column(name = "orgid", length = 20)
	private Long orgId;

	@Column(name = "fromdate")
	private LocalDate fromDate;
	@Column(name = "todate")
	private LocalDate toDate;
	@Column(name = "tcspercentage", precision = 2, scale = 2)
	private float tcsPercentage;
	@Column(name = "surpercentage", precision = 2, scale = 2)
	private float surPercentage;
	@Column(name = "edcesspercentage", precision = 2, scale = 2)
	private float edcessPercentage;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}