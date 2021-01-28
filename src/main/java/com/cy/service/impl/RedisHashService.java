package com.cy.service.impl;

import com.cy.common.constants.Constants;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RedisHashService {
	@Resource
	private RedisTemplate<String, Object> hashRedisTemplate;

	public void set(String key, String hashKey, Object value) {
		hashRedisTemplate.opsForHash().put(key, hashKey, value);
	}

	public void setAll(String key, Map<String, Object> map) {
		hashRedisTemplate.opsForHash().putAll(key, map);
	}

	public Map<String, String> getMap(String key) {
		HashOperations<String, String, String> opsForHash = hashRedisTemplate.opsForHash();
		return opsForHash.entries(key);
	}

	public Map<String, Object> get2Map(String key) {
		HashOperations<String, String, Object> opsForHash = hashRedisTemplate.opsForHash();
		return opsForHash.entries(key);
	}

	public Integer getInteger(String key, String hashKey) {
		Object obj = get(key, hashKey);
		if (obj == null) {
			return null;
		}
		if (obj instanceof Integer) {
			return Integer.parseInt(obj.toString());
		}
		return null;
	}

	public String getString(String key, String hashKey) {
		Object obj = get(key, hashKey);
		if (obj == null) {
			return null;
		}
		if (obj instanceof String) {
			return obj.toString();
		}
		return null;
	}

	public Object get(String key, String hashKey) {
		HashOperations<String, String, Object> opsForHash = hashRedisTemplate.opsForHash();
		return opsForHash.get(key, hashKey);
	}

	/**
	 * 获取字典值（这里面存的是字典的中文name）
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public String getDictionaryString(String key, String hashKey) {
		HashOperations<String, String, String> opsForHash = hashRedisTemplate.opsForHash();
		return opsForHash.get(Constants.DIC_TYPE_PREFIX + key, hashKey);
	}

	/**
	 * 获取商品类型（这里面存的商品类型的中文name）
	 *
	 * @param hashKey
	 * @return
	 */
	public String getGoodsTypeString(String hashKey) {
		HashOperations<String, String, String> opsForHash = hashRedisTemplate.opsForHash();
		return opsForHash.get(Constants.GOODS_TYPE_KEY, hashKey);
	}

	/**
	 * 事务执行——初始化商品类型
	 *
	 * @param goodsTypeKey
	 * @param map
	 */
	public void excuteInitGoodsType(String goodsTypeKey, Map<String, String> map) {
		try {
			hashRedisTemplate.execute(new SessionCallback<List<Object>>() {
				@SuppressWarnings({"unchecked", "rawtypes"})
				@Override
				public List<Object> execute(RedisOperations operations) throws DataAccessException {
					operations.multi();
					operations.delete(goodsTypeKey);
					operations.opsForHash().putAll(goodsTypeKey, map);
					return operations.exec();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
