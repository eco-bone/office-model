package com.x.office.DAO;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.x.office.entities.DevBackend;
import com.x.office.queryDTO.BEQueryDTO;

@Repository
public interface BackEndRepository extends JpaRepository<DevBackend,UUID>{
	
//	Employee e
//	Dev d 
//	Employee_Dev_map edm
//	Dev_Backend_map dbm
//	DevBackend db
	
	@Query(value = "SELECT \r\n"
			+ "NEW com.x.office.queryDTO.BEQueryDTO(e.uuid,e.address,e.name,e.dept,e.phoneNo,d.devId,d.salary,d.team,db.beId,db.designation,db.issuesLeft)\r\n"
			+ "FROM Employee e \r\n"
			+ "JOIN Employee_Dev_map edm ON e.uuid = edm.uuid \r\n"
			+ "JOIN Dev d ON edm.devId = d.devId \r\n"
			+ "JOIN Dev_Backend_map dbm ON d.devId = dbm.devId \r\n"
			+ "JOIN DevBackend db ON dbm.beId = db.beId")
	List<BEQueryDTO> findAllBackendEmployees();
}
