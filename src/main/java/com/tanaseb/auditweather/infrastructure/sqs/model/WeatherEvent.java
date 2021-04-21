package com.tanaseb.auditweather.infrastructure.sqs.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEvent {
	private String city;
	private String country;
	private Double temperature;
	private Date timestamp;
	private String type;
}
