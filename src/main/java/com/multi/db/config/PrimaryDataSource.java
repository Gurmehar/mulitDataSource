package com.multi.db.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.multi.db.primary.Users;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multi.db.primary.repo",
        entityManagerFactoryRef = "databas1EntityManagerFactory",
        transactionManagerRef= "databas1TransactionManager"
)
public class PrimaryDataSource {

  @Bean(name = "datasource1")
  @Primary
  public DataSource dataSource1(){
    return database1SourcePorperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class).build();
  }
  
  @Primary
  @Bean(name = "databas1EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean databas1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
      return builder
              .dataSource(dataSource1())
              .packages(Users.class)
              .build();
  }
  
  @Bean
  @Primary
  @ConfigurationProperties("database1.datasource")
  public DataSourceProperties database1SourcePorperties() {
    return new DataSourceProperties();
  }
  
  @Bean(name="databas1TransactionManager")
  @Primary
  public JpaTransactionManager db1TM(final @Qualifier ("databas1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1Factorybean) {
      return new JpaTransactionManager(db1Factorybean.getObject());
  }
  
  
}
