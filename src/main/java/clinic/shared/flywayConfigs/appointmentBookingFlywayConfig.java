package clinic.shared.flywayConfigs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class appointmentBookingFlywayConfig {

    @Bean(name = "appointmentBookingFlyway")
    @ConfigurationProperties(prefix = "spring.flyway.appointment-booking")
    public Flyway appointmentBookingFlyway(@Qualifier("appointmentBookingDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/appointment-booking") // Location of migration scripts
                .baselineOnMigrate(true)
                .load();
        flyway.migrate(); // Apply migrations
        return flyway;
    }
}