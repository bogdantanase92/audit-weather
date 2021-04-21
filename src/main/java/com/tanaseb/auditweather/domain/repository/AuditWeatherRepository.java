package com.tanaseb.auditweather.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tanaseb.auditweather.domain.model.AuditWeatherEntity;

@Repository
public interface AuditWeatherRepository extends CrudRepository<AuditWeatherEntity, Integer> {
}
