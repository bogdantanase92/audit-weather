package com.tanaseb.auditweather.application.mapper;

import com.tanaseb.auditweather.domain.model.AuditWeatherEntity;
import com.tanaseb.auditweather.infrastructure.sqs.model.WeatherEvent;

public class AuditWeatherMapper {

	public static AuditWeatherEntity toAuditWeatherEntity(WeatherEvent event) {
		AuditWeatherEntity entity = new AuditWeatherEntity();
		entity.setCity(event.getCity());
		entity.setCountry(event.getCountry());
		entity.setTemperature(event.getTemperature());
		entity.setTimestamp(event.getTimestamp());
		entity.setType(event.getType());

		return entity;
	}
}
