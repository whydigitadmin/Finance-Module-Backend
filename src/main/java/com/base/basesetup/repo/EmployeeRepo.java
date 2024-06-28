package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.EmployeeVO;

public interface EmployeeRepo extends JpaRepository<EmployeeVO,Long>{

	@Query(nativeQuery = true,value = "select * from employee where employeeid=?1")
	List<EmployeeVO> findEmployeeById(Long id);

	@Query(nativeQuery = true,value = "select * from employee where orgid=?1")
	List<EmployeeVO> findEmployeeByOrgId(Long orgId);

	boolean existsByEmployeeCodeAndOrgId(String employeeCode, Long orgId);

	@Query(nativeQuery = true,value = "select * from employee where active=1")
	List<EmployeeVO> findEmployeeByActive();

}
