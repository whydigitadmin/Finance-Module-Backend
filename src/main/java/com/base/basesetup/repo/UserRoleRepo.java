package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.UserRolesVO;

public interface UserRoleRepo extends JpaRepository<UserRolesVO, Long> {

}
