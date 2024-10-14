package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.ScreenNamesVO;

public interface ScreenNamesRepo extends JpaRepository<ScreenNamesVO, Long> {

	boolean existsByScreenName(String screenName);

	boolean existsByScreenCode(String screenCode);

}
