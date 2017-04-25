package com.example.first;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFirst", //
		transactionManagerRef = "mysqlTransactionManagerFirst")
public class ConfigFirst {

	@Bean
	@ConfigurationProperties(prefix = "first.db")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder //
				.create() //
				.build();
	}

	@Bean(name = "mysqlEntityManagerFirst")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mysqlDataSource()) //
				.packages(getClass().getPackage().getName())//
				.build();//
	}

	@Bean(name = "mysqlTransactionManagerFirst")
	public PlatformTransactionManager transactionManager(
			EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(
				mySqlEntityManagerFactory(builder).getObject());
		tm.setDataSource(mysqlDataSource());
		return tm;
	}
}
