package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.TaxMasterVO;

@Repository
public interface TaxMasterRepo extends JpaRepository<TaxMasterVO,Long>{

}
