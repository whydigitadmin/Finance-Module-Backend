package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.IrnCreditChargesVO;
import com.base.basesetup.entity.IrnCreditVO;

@Repository
public interface IrnCreditChargesRepo extends JpaRepository<IrnCreditChargesVO, Long>{

	List<IrnCreditChargesVO> findByIrnCreditVO(IrnCreditVO irnCreditVO);


}
