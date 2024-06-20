package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.CompanyVO;

public interface CompanyRepo extends JpaRepository<CompanyVO , Long>{

	@Query(nativeQuery = true,value = "select * from company where companyid=?1")
	List<CompanyVO> findCompanyById(Long id);

	@Query(nativeQuery = true,value = "select * from company where orgid=?1")
	List<CompanyVO> findCompanyByOrgId(Long orgId);

	boolean existsByCompanyCodeAndOrgId(String companyCode, Long orgId);

	boolean existsByCompanyNameAndOrgId(String companyName, Long orgId);

	@Query(nativeQuery = true,value = "select * from company where active=1;")
	List<CompanyVO> findCompanyByActive();

	boolean existsByCompanyCodeAndOrgIdAndId(String companyCode, Long orgId, Long id);

	boolean existsByCompanyNameAndOrgIdAndId(String companyName, Long orgId, Long id);

}
