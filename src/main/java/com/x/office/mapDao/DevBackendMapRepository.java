package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.Dev_Backend_map;

@Repository
public interface DevBackendMapRepository extends JpaRepository<Dev_Backend_map,UUID>{
//
}
