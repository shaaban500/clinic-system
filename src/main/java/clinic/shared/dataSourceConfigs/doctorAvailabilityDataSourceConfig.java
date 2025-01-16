package clinic.shared.dataSourceConfigs;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
        basePackages = "clinic.doctorAvailability.internal.data.repositories",
        entityManagerFactoryRef = "doctorAvailabilityEntityManagerFactory",
        transactionManagerRef = "doctorAvailabilityTransactionManager"
)
public class doctorAvailabilityDataSourceConfig {

    // DataSource for doctorAvailability database
    @Bean(name = "doctorAvailabilityDataSource")
    @ConfigurationProperties(prefix = "spring.doctor-availability.datasource")
    public DataSource doctorAvailabilityDataSource() {
        return DataSourceBuilder.create().build();
    }

    // EntityManagerFactory for doctorAvailability database
    @Bean(name = "doctorAvailabilityEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean doctorAvailabilityEntityManagerFactory(
            @Qualifier("doctorAvailabilityDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("clinic.doctorAvailability.internal.data.entities");
        em.setPersistenceUnitName("doctorAvailabilityPU");
        return em;
    }

    // TransactionManager for doctorAvailability database
    @Bean(name = "doctorAvailabilityTransactionManager")
    public PlatformTransactionManager doctorAvailabilityTransactionManager(
            @Qualifier("doctorAvailabilityEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}