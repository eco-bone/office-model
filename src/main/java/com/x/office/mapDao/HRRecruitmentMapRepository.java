package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.HR_Recruitment_map;

@Repository
public interface HRRecruitmentMapRepository extends JpaRepository<HR_Recruitment_map,UUID>{

}
