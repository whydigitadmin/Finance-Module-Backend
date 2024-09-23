package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.UserLoginBranchAccessibleVO;


public interface UserLoginBranchAccessibleRepo extends JpaRepository<UserLoginBranchAccessibleVO, Long> {

}
