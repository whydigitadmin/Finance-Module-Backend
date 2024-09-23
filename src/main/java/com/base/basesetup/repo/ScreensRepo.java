package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.ResponsibilityVO;
import com.base.basesetup.entity.ScreensVO;

public interface ScreensRepo extends JpaRepository<ScreensVO, Long> {

	List<ScreensVO> findByResponsibilityVO(ResponsibilityVO responsibilityVO);

}
