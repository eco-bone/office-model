package com.x.office.maps;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HR_PM_map {
	
	@Id
	private UUID hrId;
	private UUID pmId;
}
