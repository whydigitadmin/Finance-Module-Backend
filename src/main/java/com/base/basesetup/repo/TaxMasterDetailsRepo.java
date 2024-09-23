package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TaxMasterDetailsVO;

public interface TaxMasterDetailsRepo extends JpaRepository<TaxMasterDetailsVO, Long>{

}
