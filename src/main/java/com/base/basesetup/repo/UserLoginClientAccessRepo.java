package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.UserLoginClientAccessVO;

public interface UserLoginClientAccessRepo extends JpaRepository<UserLoginClientAccessVO, Long> {

}
