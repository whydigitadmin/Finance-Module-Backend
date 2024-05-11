package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.CityVO;

public interface CityRepo extends JpaRepository<CityVO, Long> {

	@Query(nativeQuery = true, value = "select * from city where cityid=?1")
	List<CityVO> findCityById(Long id);

	@Query(nativeQuery = true, value = "select * from city where orgid=?1")
	List<CityVO> findCityByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from city where orgid=?1 and state=?2")
	List<CityVO> findAllCityByState(Long orgId, String state);

}
