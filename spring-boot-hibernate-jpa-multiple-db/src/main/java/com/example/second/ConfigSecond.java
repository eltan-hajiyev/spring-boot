package com.example.second;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Elten Hajiyev
 * 
 *         Repository and Entity classes must be under location of this
 *         configuration class
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerSecond", //
		transactionManagerRef = "mysqlTransactionManagerSecond")
public class ConfigSecond {
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "second.db")
	public DataSource mysqlDataSourceSecond() {
		return DataSourceBuilder //
				.create() //
				.build();
	}

	@Bean(name = "mysqlEntityManagerSecond")
	@Primary
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactorySecond(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mysqlDataSourceSecond()) //
				.packages(getClass().getPackage().getName()) //
				.build();//
	}

	@Bean(name = "mysqlTransactionManagerSecond")
	public PlatformTransactionManager transactionManager(
			EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(
				mySqlEntityManagerFactorySecond(builder).getObject());
		tm.setDataSource(mysqlDataSourceSecond());
		return tm;
	}
}
