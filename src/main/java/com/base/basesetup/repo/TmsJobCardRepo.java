package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.TmsJobCardVO;

@Repository
public interface TmsJobCardRepo extends JpaRepository<TmsJobCardVO, Long> {

	@Query(nativeQuery = true, value = "select * from tmsjobcard where orgid=?1")
	List<TmsJobCardVO> getAllTmsJobCardByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from tmsjobcard where tmsjobcardid=?1")
	List<TmsJobCardVO> getAllTmsJobCardById(Long id);

	@Query(nativeQuery = true, value = "select * from tmsjobcard where active=1")
	List<TmsJobCardVO> findTmsJobCardByActive();

	@Query(nativeQuery = true, value = "select concat(a.employee,' - ',a.employeecode)as employee from employee a where active=1 and orgid=?1 and\r\n"
			+ " branch=?2 and branchcode=?3 group by concat(a.employee,' - ',a.employeecode) order by employee")
	Set<Object[]> findByEmployeeNameAndEmployeeCode(Long orgId,String branch,String branchCode);

}
