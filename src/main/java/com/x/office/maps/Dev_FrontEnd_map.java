package com.x.office.maps;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Dev_FrontEnd_map {
	@Id
	private UUID devId;
	private UUID feId;
}
