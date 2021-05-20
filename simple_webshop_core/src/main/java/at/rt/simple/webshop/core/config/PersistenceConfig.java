package at.rt.simple.webshop.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring Konfiguration fuer DB-Zugriff mit JPA, Datasource, Transaktionen, Spring Data Repositories.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    /**
     * Uebernimmt die Uebersetzung von Exceptions des Persistence Providers in allgemeine
     * Spring DataAccessExceptions.
     *
     * @return Prozessor fuer Exceptions
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Uebernimmt die Uebersetzung von Exceptions des Persistence Providers in allgemeine
     * Spring DataAccessExceptions.
     * <p>
     * Anmerkung: Per default werden nur mit
     * {@link org.springframework.stereotype.Repository} annotierte Klassen
     * beruecksichtigt. Damit die auch Exceptions aus den Services uebersetzt werden,
     * benoetigen wir einen zweiten PersistenceExceptionTranslationPostProcessor oder
     * annotieren die entsprechenden Services zusaetzlich mit Repository.
     * </p>
     *
     * @return Prozessor fuer Exceptions
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor serviceExceptionTranslation() {
        PersistenceExceptionTranslationPostProcessor postProcessor = new PersistenceExceptionTranslationPostProcessor();
        postProcessor.setRepositoryAnnotationType(Service.class);

        return postProcessor;
    }

    /**
     * Konfiguration fuer die EntityManagerFactory und JPA.
     *
     * @return FactoryBean zur Erstellung der EntityManagerFactory
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("at.rt.simple.webshop.core.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.jdbc.fetch_size", "15");
        jpaProperties.setProperty("hibernate.bytecode.use_reflection_optimizer", "true");
        jpaProperties.setProperty("hibernate.format_sql", "true");
        jpaProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        jpaProperties.setProperty("hibernate.order_updates", "false");
        jpaProperties.setProperty("hibernate.default_batch_fetch_size", "4");
        jpaProperties.setProperty("hibernate.id.new_generator_mappings", "true");

        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(jpaProperties);

        return factoryBean;
    }

    /**
     * H2-Datasource fuer die Sample-DB incl. Test-SQL Scripts.
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.setName("testdb");
        builder.setScriptEncoding("UTF-8");
        builder.addScript("classpath:/db/h2-schema.sql");
        builder.addScript("classpath:/db/test-data-kunde.sql");
        builder.addScript("classpath:/db/test-data-bestellung.sql");
        builder.addScript("classpath:/db/test-data-produkt.sql");

        return builder.build();
    }
}
