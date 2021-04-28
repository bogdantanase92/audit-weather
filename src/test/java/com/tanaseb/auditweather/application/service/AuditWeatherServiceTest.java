package com.tanaseb.auditweather.application.service;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanaseb.auditweather.AuditWeatherApplication;
import com.tanaseb.auditweather.domain.model.AuditWeatherEntity;
import com.tanaseb.auditweather.domain.repository.AuditWeatherRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AuditWeatherApplication.class})
class AuditWeatherServiceTest {

	public static final String CITY = "Cluj";
	public static final String COUNTRY = "RO";
	public static final String TEMPERATURE = "285.65";
	public static final String TIMESTAMP = "2021-04-28T18:54:18.649+00:00";
	public static final String TYPE = "created";

	@Autowired
	private AuditWeatherService auditWeatherService;
	@Autowired
	private AuditWeatherRepository repository;
	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void receive() throws JsonProcessingException {
		String message = buildMessage();
		auditWeatherService.receive(message);

		Optional<AuditWeatherEntity> optionalEntity = repository.findById(1);
		optionalEntity.ifPresent(entity -> {
					Assertions.assertEquals(CITY, entity.getCity());
					Assertions.assertEquals(COUNTRY, entity.getCountry());
					Assertions.assertEquals(Double.valueOf(TEMPERATURE), entity.getTemperature());
					Assertions.assertEquals(TYPE, entity.getType());
				}
		);
	}

	private String buildMessage() throws JsonProcessingException {
		Map<String, String> json = Map.of(
				"city", CITY,
				"country", COUNTRY,
				"temperature", TEMPERATURE,
				"timestamp", TIMESTAMP,
				"type", TYPE
		);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
	}
}