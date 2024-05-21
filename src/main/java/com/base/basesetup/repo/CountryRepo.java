package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.CountryVO;

public interface CountryRepo extends JpaRepository<CountryVO, Long> {

	@Query(nativeQuery = true, value = "select * from country where countryid=?1")
	List<CountryVO> findCountryById(Long id);

	@Query(nativeQuery = true, value = "select * from country where orgid=?1")
	List<CountryVO> findCountryByOrgId(Long orgId);

	boolean existsByCountryNameAndOrgId(String countryName, Long orgId);

	boolean existsByCountryCodeAndOrgId(String countryCode, Long orgId);

	@Query(nativeQuery = true, value = "select * from country where active=1")
	List<CountryVO> findCountryByActive();

}
