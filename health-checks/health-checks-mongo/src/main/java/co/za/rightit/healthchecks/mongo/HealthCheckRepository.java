package co.za.rightit.healthchecks.mongo;

import java.util.Optional;

import co.za.rightit.healthchecks.model.HealthCheck;

public interface HealthCheckRepository {
	HealthCheck createHealthCheck(HealthCheck healthCheck);
	Optional<HealthCheck> getHealthCheck(String id);
	boolean updateHealthCheck(HealthCheck healthCheck);
}
