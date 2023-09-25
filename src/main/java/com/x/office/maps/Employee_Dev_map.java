package com.x.office.maps;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee_Dev_map {
	
	@Id
	private UUID uuid;
	private UUID devId;
}
