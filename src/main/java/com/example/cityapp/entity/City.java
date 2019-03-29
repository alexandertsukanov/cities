package com.example.cityapp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class City {

	@Id
	private String id;

	private String name;

	private String country;

}
