package clinic.shared.flywayConfigs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class appointmentBookingFlywayConfig {

    @Bean(name = "appointmentBookingFlyway")
//    @ConfigurationProperties(prefix = "spring.flyway.appointment-booking")
    @DependsOn("appointmentBookingDataSource")
    public Flyway appointmentBookingFlyway(@Qualifier("appointmentBookingDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .outOfOrder(false)
                .baselineOnMigrate(false)
                .locations("classpath:db/migration/appointment-booking")
                .schemas("appointment-booking")
                .load();
        flyway.migrate(); // Apply migrations
        return flyway;
    }
}