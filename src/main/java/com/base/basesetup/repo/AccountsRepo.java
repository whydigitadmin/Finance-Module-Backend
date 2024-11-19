package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.AccountsVO;

public interface AccountsRepo extends JpaRepository<AccountsVO, Long> {

}
