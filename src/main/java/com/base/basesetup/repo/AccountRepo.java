package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, Long>{

}
