package com.base.basesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostCenterDTO {
private Long id;
private String dimensionType;
private String valueCode;
private String valueDescripition;
private String createdBy;
private String updatedBy;
private Long orgId;
private boolean active;

}