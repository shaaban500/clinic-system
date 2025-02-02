package clinic.appointmentBooking.internal.infrastructure.configs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
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
                .outOfOrder(true)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration/appointment-booking")
                .schemas("appointment-booking")
                .load();

        flyway.migrate();
        return flyway;
    }
}