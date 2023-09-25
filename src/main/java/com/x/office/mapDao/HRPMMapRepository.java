package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.HR_PM_map;

@Repository
public interface HRPMMapRepository extends JpaRepository<HR_PM_map,UUID>{

}
