package com.tanaseb.auditweather.application.service;

import static com.tanaseb.auditweather.application.mapper.AuditWeatherMapper.toAuditWeatherEntity;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanaseb.auditweather.domain.model.AuditWeatherEntity;
import com.tanaseb.auditweather.domain.repository.AuditWeatherRepository;
import com.tanaseb.auditweather.infrastructure.sqs.model.WeatherEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditWeatherService {

	private final AuditWeatherRepository auditWeatherRepository;
	private final ObjectMapper mapper;

	public AuditWeatherService(AuditWeatherRepository auditWeatherRepository, ObjectMapper mapper) {
		this.auditWeatherRepository = auditWeatherRepository;
		this.mapper = mapper;
	}

	@JmsListener(destination = "weather")
	@Transactional
	public void receive(String message) {
		log.info("Receiving from SQS event: {}", message);

		WeatherEvent event = createEvent(message);
		AuditWeatherEntity entity = toAuditWeatherEntity(event);
		auditWeatherRepository.save(entity);
	}

	private WeatherEvent createEvent(String message) {
		WeatherEvent event;
		try {
			event = mapper.readValue(message, WeatherEvent.class);
		} catch (JsonProcessingException e) {
			log.error("Event could not be deserialized");
			throw new RuntimeException();
		}
		return event;
	}
}
