package com.x.office.DAO;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.entities.HR;

@Repository
public interface HrRepository extends JpaRepository<HR,UUID>{

}
