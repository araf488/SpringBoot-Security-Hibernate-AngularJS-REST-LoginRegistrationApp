package com.spring.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.spring.interceptor.JWTInteceptor;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.spring.*")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.spring.repository")

public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Resource
	private Environment env;

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("spring.jpa.show-sql", "true");
		properties.put("hibernate.show-sql", "true");
		properties.put("spring.jpa.hibernate.ddl-auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("spring.jpa.properties.hibernate.dialect", MySQL5Dialect.class.getName());
		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));

		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		va.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean lcfb = new LocalContainerEntityManagerFactoryBean();
		lcfb.setDataSource(dataSource());
		lcfb.setPackagesToScan("com.spring.models");
		lcfb.setJpaVendorAdapter(va);
		lcfb.setJpaProperties(hibProperties());
		lcfb.afterPropertiesSet();
		return lcfb.getObject();

	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(entityManagerFactory());
		return tx;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInteceptor()).addPathPatterns("/data/*").excludePathPatterns("/")
				.excludePathPatterns("login/");

	}

	@Bean
	public JWTInteceptor jwtInteceptor() {
		return new JWTInteceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

}