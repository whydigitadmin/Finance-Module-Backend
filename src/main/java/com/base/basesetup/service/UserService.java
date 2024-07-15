package com.base.basesetup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ChangePasswordFormDTO;
import com.base.basesetup.dto.CreateUserFormDTO;
import com.base.basesetup.dto.LoginFormDTO;
import com.base.basesetup.dto.ResetPasswordFormDTO;
import com.base.basesetup.dto.SignUpFormDTO;
import com.base.basesetup.dto.UserResponseDTO;
import com.base.basesetup.entity.UserVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface UserService {

	public void signup(SignUpFormDTO signUpRequest);

	public UserResponseDTO login(LoginFormDTO loginRequest);

	public void logout(String userName);

	public void changePassword(ChangePasswordFormDTO changePasswordRequest);

	public void resetPassword(ResetPasswordFormDTO resetPasswordRequest);

	public UserVO getUserById(Long userId);

	public UserVO getUserByUserName(String userName);

	public void createUserAction(String userName, long userId, String actionType);

	public void removeUser(String userName);

	String createUser(CreateUserFormDTO createUserFormDTO) throws ApplicationException;

	public List<UserVO> getUserByOrgId(Long orgId);

}
