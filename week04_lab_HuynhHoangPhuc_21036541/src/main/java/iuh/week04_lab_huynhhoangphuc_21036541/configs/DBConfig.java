package iuh.week04_lab_huynhhoangphuc_21036541.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
   @Bean
   @Scope("singleton")
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
      dataSource.setUrl("jdbc:mariadb://localhost:3306/lab05?createDatabaseIfNotExist=true");
      dataSource.setUsername("root");
      dataSource.setPassword("anquocviet_203");
      return dataSource;
   }
}
