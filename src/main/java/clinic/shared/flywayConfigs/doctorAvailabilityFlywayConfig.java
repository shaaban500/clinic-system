package clinic.shared.flywayConfigs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class doctorAvailabilityFlywayConfig {

    @Bean(name = "doctorAvailabilityFlyway")
    @ConfigurationProperties(prefix = "spring.flyway.doctor-availability")
    public Flyway doctorAvailabilityFlyway(@Qualifier("doctorAvailabilityDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/doctor-availability") // Location of migration scripts
                .baselineOnMigrate(true)
                .load();
        flyway.migrate(); // Apply migrations
        return flyway;
    }
}