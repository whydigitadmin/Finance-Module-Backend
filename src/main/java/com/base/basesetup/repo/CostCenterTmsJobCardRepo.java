package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostCenterTmsJobCardVO;
import com.base.basesetup.entity.TmsJobCardVO;
@Repository
public interface CostCenterTmsJobCardRepo extends JpaRepository<CostCenterTmsJobCardVO, Long> {

	List<CostCenterTmsJobCardVO> findByTmsJobCardVO(TmsJobCardVO tmsJobCardVO);

}
