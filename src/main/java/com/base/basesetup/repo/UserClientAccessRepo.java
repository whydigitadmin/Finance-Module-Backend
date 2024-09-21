package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.UserLoginClientAccessVO;
import com.base.basesetup.entity.UserVO;

public interface UserClientAccessRepo extends JpaRepository<UserLoginClientAccessVO,Long> {

	List<UserLoginClientAccessVO> findByUserVO(UserVO userVO);

}