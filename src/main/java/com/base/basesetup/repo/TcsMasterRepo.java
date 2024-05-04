package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.TcsMasterVO;

public interface TcsMasterRepo extends JpaRepository<TcsMasterVO, Long> {

	
	@Query(value="Select * from tcsmaster where orgid=?1",nativeQuery = true)
	List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId);

}
