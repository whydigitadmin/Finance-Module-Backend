package com.base.basesetup.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "debitnote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitNoteVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debitnotegen")
	@SequenceGenerator(name = "debitnotegen", sequenceName = "debitnoteVO", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "debitnoteid")
	private Long id;
	@Column(name = "mode")
	private String mode;
}
