package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.StateVO;

@Repository
public interface StateRepo extends JpaRepository<StateVO, Long> {

	@Query(nativeQuery = true, value = "select * from state where stateid=?1")
	List<StateVO> findStateById(Long id);

	@Query(nativeQuery = true, value = "select * from state where orgid=?1")
	List<StateVO> findStateByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from state where orgid=?1 and country=?2")
	List<StateVO> findAllStateByCountry(Long orgId, String country);

	boolean existsByStateNameAndOrgId(String stateName, Long orgId);

	boolean existsByStateCodeAndOrgId(String stateCode, Long orgId);

}
