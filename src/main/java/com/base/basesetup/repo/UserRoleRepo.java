package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.UserRolesVO;
import com.base.basesetup.entity.UserVO;

public interface UserRoleRepo extends JpaRepository<UserRolesVO, Long> {

	List<UserRolesVO> findByUserVO(UserVO userVO);

}
