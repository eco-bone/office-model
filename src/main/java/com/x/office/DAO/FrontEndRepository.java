package com.x.office.DAO;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.x.office.entities.DevFrontEnd;
import com.x.office.queryDTO.FEQueryDTO;

@Repository
public interface FrontEndRepository extends JpaRepository<DevFrontEnd,UUID>{
	@Query(value = "SELECT \r\n"
			+ "NEW com.x.office.queryDTO.FEQueryDTO(e.uuid,e.address,e.name,e.dept,e.phoneNo,d.devId,d.salary,d.team,df.feId,df.designation,df.issuesLeft)\r\n"
			+ "FROM Employee e \r\n"
			+ "JOIN Employee_Dev_map edm ON e.uuid = edm.uuid \r\n"
			+ "JOIN Dev d ON d.devId = edm.devId \r\n"
			+ "JOIN Dev_FrontEnd_map dfm ON d.devId = dfm.devId \r\n"
			+ "JOIN DevFrontEnd df ON dfm.feId = df.feId")
	List<FEQueryDTO> findAllFrontEndEmployees();
}
