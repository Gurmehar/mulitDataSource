package com.multi.db.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
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
@EnableJpaRepositories(basePackages = "com.multi.db.secondary.repo",
        entityManagerFactoryRef = "database2EntityManagerFactory",
        transactionManagerRef= "database2TransactionManager"
)
public class SecondaryDataSource {
  
  @Bean(name = "datasource2")
  public DataSource dataSource2(){
      return database2SourcePorperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
  }
  
  @Bean
  @ConfigurationProperties("database2.datasource")
  public DataSourceProperties database2SourcePorperties() {
    return new DataSourceProperties();
  }
  
  
  @Bean(name = "database2EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean databas2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
      return builder
              .dataSource(dataSource2())
              .packages("com.multi.db.secondary")
              .build();
  }

  
 
  @Bean(name="database2TransactionManager")
  public JpaTransactionManager db2TM(final @Qualifier ("database2EntityManagerFactory") LocalContainerEntityManagerFactoryBean db2Factorybean) {
      return new JpaTransactionManager(db2Factorybean.getObject());
  }

}
