package com.cy;

import com.cy.service.impl.RedisHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 初始化redis缓存
 *
 */
@Component
public class InitRedisCache implements ApplicationRunner {
	@Autowired
	private RedisHashService redisHashService;

	public final Logger log = LoggerFactory.getLogger(InitRedisCache.class);

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// 初始化Redis字典缓存
		initDictionary();
	}

	@Async // 异步——独立线程启动
	void initDictionary() {
	}

}
