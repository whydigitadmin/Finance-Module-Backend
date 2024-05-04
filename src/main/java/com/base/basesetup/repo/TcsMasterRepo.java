package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TcsMasterVO;

public interface TcsMasterRepo extends JpaRepository<TcsMasterVO, Long> {

}
