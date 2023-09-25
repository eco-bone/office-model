package com.x.office.mapDao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.maps.Employee_HR_map;

@Repository
public interface EmployeeHRMapRepository extends JpaRepository<Employee_HR_map,UUID>{

}
