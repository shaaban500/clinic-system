package clinic.appointmentManagement.internal.shell.db.configs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class AppointmentManagementFlywayConfig {

    @Bean(name = "appointmentManagementFlyway")
    @DependsOn("appointmentManagementDataSource")
    public Flyway appointmentManagementFlyway(@Qualifier("appointmentManagementDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .outOfOrder(true)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration/appointment-management")
                .schemas("appointment-management")
                .load();

        flyway.migrate();
        return flyway;
    }
}


