package clinic.shared.dataSourceConfigs;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "clinic.appointmentBooking.infrastructure.repositories", // Repository package for appointmentBooking
        entityManagerFactoryRef = "appointmentBookingEntityManagerFactory",
        transactionManagerRef = "appointmentBookingTransactionManager"
)
public class appointmentBookingDataSourceConfig {

    // DataSource for appointmentBooking database
    @Primary
    @Bean(name = "appointmentBookingDataSource")
    @ConfigurationProperties(prefix = "spring.appointment-booking.datasource")
    public DataSource appointmentBookingDataSource() throws SQLException {
        DataSource dataSource = DataSourceBuilder.create().build();
        System.out.println("Appointment Booking DataSource URL: " + dataSource.getConnection().getMetaData().getURL());
        return dataSource;
    }

    // EntityManagerFactory for appointmentBooking database
    @Primary
    @Bean(name = "appointmentBookingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appointmentBookingEntityManagerFactory(
            @Qualifier("appointmentBookingDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("clinic.appointmentBooking.infrastructure.entities"); // Entity package for appointmentBooking
        em.setPersistenceUnitName("appointmentBookingPU");
        return em;
    }

    // TransactionManager for appointmentBooking database
    @Primary
    @Bean(name = "appointmentBookingTransactionManager")
    public PlatformTransactionManager appointmentBookingTransactionManager(
            @Qualifier("appointmentBookingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}