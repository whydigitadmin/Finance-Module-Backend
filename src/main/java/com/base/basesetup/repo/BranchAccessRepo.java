package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.BranchAccessVO;

public interface BranchAccessRepo extends JpaRepository<BranchAccessVO, Long> {

}
