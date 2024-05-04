package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.TaxMaster2VO;

@Repository
public interface TaxMaster2Repo extends JpaRepository<TaxMaster2VO, Long>{

}
