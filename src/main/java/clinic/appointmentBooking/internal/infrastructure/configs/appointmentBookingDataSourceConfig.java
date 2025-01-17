package clinic.appointmentBooking.internal.infrastructure.configs;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "clinic.appointmentBooking.infrastructure.repositories", // Repository package for appointmentBooking
        entityManagerFactoryRef = "appointmentBookingEntityManagerFactory",
        transactionManagerRef = "appointmentBookingTransactionManager"
)
public class appointmentBookingDataSourceConfig {

    // DataSource for appointmentBooking database
    @Bean(name = "appointmentBookingDataSource")
    @ConfigurationProperties(prefix = "spring.appointment-booking.datasource")
    public DataSource appointmentBookingDataSource() {
        return DataSourceBuilder.create().build();
    }

    // EntityManagerFactory for appointmentBooking database
    @Bean(name = "appointmentBookingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appointmentBookingEntityManagerFactory(
            @Qualifier("appointmentBookingDataSource") DataSource dataSource,
            final EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource)
                .packages("clinic.appointmentBooking.infrastructure.entities")
                .persistenceUnit("appointmentBooking")
                .build();
    }

    // TransactionManager for appointmentBooking database
    @Bean(name = "appointmentBookingTransactionManager")
    public PlatformTransactionManager appointmentBookingTransactionManager(
            @Qualifier("appointmentBookingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}