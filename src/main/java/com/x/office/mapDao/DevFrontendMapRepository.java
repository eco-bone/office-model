package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.Dev_FrontEnd_map;

@Repository
public interface DevFrontendMapRepository extends JpaRepository<Dev_FrontEnd_map,UUID>{

}
