package com.bitc.board.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySources({
	@PropertySource("classpath:prop/db.properties")
})
@ComponentScan(basePackages = {"com.bitc.board.service"})
@MapperScan(basePackages = {"com.bitc.board.dao"})
public class RootConfig {
	@Value("${jdbc.log4j.driver}")
	private String driver;
	@Value("${jdbc.log4j.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@PostConstruct
	public void init() {
		System.out.println("init -------------------");
		System.out.println("driver : " + driver);
		System.out.println("jdbcUrl : " + jdbcUrl);
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("init -------------------");
	}
	
	@Bean("hc")
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(20);
		return hikariConfig;
	}
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory
			= new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
//		sqlSessionFactory.setTypeAliasesPackage("com.bitc.board.vo, com.bitc.board.util");
//		Resource res = new ClassPathResource("mybatis/sql/boardMapper.xml");
//		sqlSessionFactory.setMapperLocations(res);
		return sqlSessionFactory.getObject();
	}
	
}
