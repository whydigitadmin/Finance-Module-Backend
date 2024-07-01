package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.ScreenVO;

public interface ScreenRepo extends JpaRepository<ScreenVO, Long>{

}
