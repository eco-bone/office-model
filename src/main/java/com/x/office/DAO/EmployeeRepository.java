package com.x.office.DAO;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.x.office.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,UUID>{

}
