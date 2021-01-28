package com.cy.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
	@Value("${spring.redis.database}")
	private Integer database;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Value(value="${spring.redis.password}")
	private String password;

	@Value("${spring.redis.pool.max-active}")
	private Integer maxActive;

	@Value("${spring.redis.pool.max-wait}")
	private Integer maxWait;

	@Value("${spring.redis.pool.max-idle}")
	private Integer maxIdle;

	@Value("${spring.redis.pool.min-idle}")
	private Integer minIdle;

	@Value("${spring.redis.timeout}")
	private Integer timeout;

	public Integer getDatabase() {
		return database;
	}

	public void setDatabase(Integer database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

}