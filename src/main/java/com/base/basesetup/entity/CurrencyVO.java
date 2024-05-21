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
@Table(name = "currency")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currencygen")
	@SequenceGenerator(name = "currencygen", sequenceName = "currencyVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "currencyid")
	private Long id;

	@Column(name = "userid")
	private String userId;
	@Column(name = "country")
	private String country;
	@Column(name = "currency")
	private String currency;
	@Column(name = "subcurrency")
	private String subCurrency;
	@Column(name = "currencysymbol")
	private String currencySymbol;
	@Column(name = "orgid")
	private Long orgId;

	@Column(name = "active")
	private boolean active;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedby")
	private String updatedBy;
	@Column(name = "cancel")
	private boolean cancel;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
