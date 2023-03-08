package xyz.tham.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

//@Readiness
@ApplicationScoped
public class DatabaseConnectionCheck implements HealthCheck {
    private boolean isDbUp = false;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse
                .named("Db connection health check");
        try {
            simulateDbConnection();
            builder.up();
        } catch (IllegalStateException e) {
            builder.down();
        }
        return builder.build();
    }

    private void simulateDbConnection() {
        if (!isDbUp) {
            throw new IllegalStateException("Database is down");
        }
    }
}
