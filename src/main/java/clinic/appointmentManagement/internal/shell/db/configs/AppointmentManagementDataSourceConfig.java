package clinic.appointmentManagement.internal.shell.db.configs;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "clinic.appointmentManagement.internal.shell.db.repositories",
        entityManagerFactoryRef = "appointmentManagementEntityManagerFactory",
        transactionManagerRef = "appointmentManagementTransactionManager"
)
public class AppointmentManagementDataSourceConfig {

    // DataSource for appointmentManagement database
    @Bean(name = "appointmentManagementDataSource")
    @ConfigurationProperties(prefix = "spring.appointment-management.datasource")
    public DataSource appointmentManagementDataSource() {
        return DataSourceBuilder.create().build();
    }

    // EntityManagerFactory for appointmentManagement database
    @Bean(name = "appointmentManagementEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appointmentManagementEntityManagerFactory(
            @Qualifier("appointmentManagementDataSource") DataSource dataSource,
            final EntityManagerFactoryBuilder builder) throws SQLException {

        return builder
                .dataSource(dataSource)
                .packages("clinic.appointmentManagement.internal.shell.db.entities")
                .persistenceUnit("appointmentManagement")
                .build();
    }

    // TransactionManager for appointmentManagement database
    @Bean(name = "appointmentManagementTransactionManager")
    public PlatformTransactionManager appointmentManagementTransactionManager(
            @Qualifier("appointmentManagementEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
