package com.x.office.DAO;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.entities.Dev;

@Repository
public interface DevRepository extends JpaRepository<Dev,UUID>{
	
}
