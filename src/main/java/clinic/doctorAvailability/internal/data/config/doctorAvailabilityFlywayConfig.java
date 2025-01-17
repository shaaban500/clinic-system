package clinic.doctorAvailability.internal.data.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.InfoResult;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class doctorAvailabilityFlywayConfig {

    @Bean(name = "doctorAvailabilityFlyway")
    @DependsOn("doctorAvailabilityDataSource")
    public Flyway doctorAvailabilityFlyway(@Qualifier("doctorAvailabilityDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .outOfOrder(true)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration/doctor-availability")
                .schemas("doctor-availability")
                .load();

        flyway.migrate();
        return flyway;
    }
}