package com.bitc.board.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.zaxxer.hikari.HikariConfig;

@Configuration
@PropertySources({
	@PropertySource("classpath:prop/db.properties")
})
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
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return hikariConfig;
	}
	
}
