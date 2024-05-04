package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.TdsMasterVO;

@Repository

public interface TdsMasterRepo extends JpaRepository<TdsMasterVO, Long>{

}
