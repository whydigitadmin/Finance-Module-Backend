package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.SetTaxRateVO;

@Repository
public interface SetTaxRateRepo extends JpaRepository<SetTaxRateVO, Long>{
       
}
