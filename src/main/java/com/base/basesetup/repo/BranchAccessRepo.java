package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.BranchAccessVO;
import com.base.basesetup.entity.UserVO;

public interface BranchAccessRepo extends JpaRepository<BranchAccessVO, Long> {

	List<BranchAccessVO> findByUserVO(UserVO userVO);

	@Query(nativeQuery = true, value = "select a.branch, a.branchcode from branch a,branchaccess b,users c where a.branchcode=b.branch and b.userid=c.userid and a.active=1 and c.userid=?1 and a.orgid=c.orgid")
	Set<Object[]> getUserAccessBranch(Long userId);

}