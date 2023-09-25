package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.Employee_Dev_map;

@Repository
public interface EmployeeDevMapRepository extends JpaRepository<Employee_Dev_map,UUID>{

}
