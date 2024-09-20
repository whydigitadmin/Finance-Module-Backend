package com.base.basesetup.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.BranchAccessDTO;
import com.base.basesetup.dto.ChangePasswordFormDTO;
import com.base.basesetup.dto.CreateUserFormDTO;
import com.base.basesetup.dto.LoginFormDTO;
import com.base.basesetup.dto.RefreshTokenDTO;
import com.base.basesetup.dto.ResetPasswordFormDTO;
import com.base.basesetup.dto.SignUpFormDTO;
import com.base.basesetup.dto.UserResponseDTO;
import com.base.basesetup.dto.UserRoleDTO;
import com.base.basesetup.entity.BranchAccessVO;
import com.base.basesetup.entity.GlobalParameterVO;
import com.base.basesetup.entity.TokenVO;
import com.base.basesetup.entity.UserActionVO;
import com.base.basesetup.entity.UserRolesVO;
import com.base.basesetup.entity.UserVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.BranchAccessRepo;
import com.base.basesetup.repo.GlobalParameterRepo;
import com.base.basesetup.repo.TokenRepo;
import com.base.basesetup.repo.UserActionRepo;
import com.base.basesetup.repo.UserRepo;
import com.base.basesetup.repo.UserRoleRepo;
import com.base.basesetup.security.TokenProvider;
import com.base.basesetup.util.CryptoUtils;

@Service
public class UserServiceImpl implements UserService {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserActionRepo userActionRepo;

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	TokenRepo tokenRepo;

	public void createUserAction(String userName, Long usersId, String actionType) {
		try {
			UserActionVO userActionVO = new UserActionVO();
			userActionVO.setUserName(userName);
			userActionVO.setUserId(usersId);
			userActionVO.setActionType(actionType);
			userActionRepo.save(userActionVO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	

	@Override
	public void removeUser(String userName) {
		String methodName = "removeUser()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		if (StringUtils.isNotEmpty(userName)) {
			UserVO userVO = userRepo.findByUserName(userName);
			if (ObjectUtils.isEmpty(userVO)) {
				throw new ApplicationContextException(UserConstants.ERRROR_MSG_USER_INFORMATION_NOT_FOUND);
			}
//			userVO.setActive(false);
			userVO.setAccountRemovedDate(new Date());
			userRepo.save(userVO);
			createUserAction(userVO.getUserName(), userVO.getId(), UserConstants.USER_ACTION_REMOVE_ACCOUNT);
		} else {
			throw new ApplicationContextException(UserConstants.ERRROR_MSG_INVALID_USER_NAME);
		}
	}

	

}
