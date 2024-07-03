package com.base.basesetup.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private long userId;
	private long orgId;
	private String employeeName;
	private String email;
	private String userName;
	private boolean loginStatus;
	private boolean isActive;
	private Date accountRemovedDate;
	private String token;
	private String tokenId;
	private String lastLogin;
	private List<UserRoleDTO> userRoles;
    private List<BranchAccessDTO> branchAccess;
}
