package clinic.doctorAvailability.internal.data.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "clinic.doctorAvailability.internal.data.repositories",
        entityManagerFactoryRef = "doctorAvailabilityEntityManagerFactory",
        transactionManagerRef = "doctorAvailabilityTransactionManager"
)
public class doctorAvailabilityDataSourceConfig {

    // DataSource for doctorAvailability database
    @Primary
    @Bean(name = "doctorAvailabilityDataSource")
    @ConfigurationProperties(prefix = "spring.doctor-availability.datasource")
    public DataSource doctorAvailabilityDataSource() {
        return DataSourceBuilder.create().build();
    }

    // EntityManagerFactory for doctorAvailability database
    @Primary
    @Bean(name = "doctorAvailabilityEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean doctorAvailabilityEntityManagerFactory(
            @Qualifier("doctorAvailabilityDataSource") DataSource dataSource,
            final EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource)
                .packages("clinic.doctorAvailability.internal.data.entities")
                .persistenceUnit("doctorAvailability")
                .build();
    }

    // TransactionManager for doctorAvailability database
    @Bean(name = "doctorAvailabilityTransactionManager")
    @Primary
    public PlatformTransactionManager doctorAvailabilityTransactionManager(
            @Qualifier("doctorAvailabilityEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    @Primary
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}