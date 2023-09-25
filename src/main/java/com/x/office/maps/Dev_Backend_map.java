package com.x.office.maps;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Dev_Backend_map {
	
	@Id
	private UUID devId;
	private UUID beId;
}
