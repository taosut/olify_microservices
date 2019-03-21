package com.olify.eprice.microservice.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.olify.eprice.microservice.category.Repository.CategoryRepository;
import com.olify.eprice.microservice.category.Service.CategoryService;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@ConfigurationProperties(prefix="spring.datasource.hikari")
public class CategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.olify.eprice.microservice.category");
		bean.setPersistenceUnitName("Categorizing Items");
		bean.setJpaVendorAdapter(getJpaVendorAdapter());
		bean.afterPropertiesSet();
		bean.setDataSource(dataSource());
		return bean;
	}
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
		return adapter; 
	}
	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setMaximumPoolSize(100);
		//dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		//dataSource.setJdbcUrl("jdbc:h2:mem:TEST");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/olify_markets");
		dataSource.setUsername("root");
		dataSource.setPassword("olify");
		dataSource.setConnectionTestQuery("SELECT 1");
		dataSource.setPoolName("HikariCP");
		dataSource.setIdleTimeout(10000);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("useSSL", false);
        
        //HikariDataSource hikariConfig = new HikariDataSource(dataSource);
		return dataSource;
	}
	
	@Bean(name="categoryService")
	public CategoryService categoryService() {
		return new CategoryService();
	}
	
	@Bean(name="categoryRepository")
	public CategoryRepository categoryRepository() {
		return categoryRepository();
	}
}