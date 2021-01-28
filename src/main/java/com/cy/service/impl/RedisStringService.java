package com.cy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisStringService {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	public void set(String key, String value) {
		set(key, value, Constants.REDIS_EXPIRY_TIME_THIRTY_MINUTE, TimeUnit.SECONDS);
	}

	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	public void set(String key, String value, long timeout) {
		set(key, value, timeout, TimeUnit.SECONDS);
	}

	public void set(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void expire(String key) {
		expire(key, Constants.REDIS_EXPIRY_TIME_THIRTY_MINUTE);
	}

	public void expire(String key, long timeout) {
		expire(key, timeout, TimeUnit.SECONDS);
	}

	public void expire(String key, long timeout, TimeUnit unit) {
		stringRedisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 事务执行更新Token超时时间
	 *
	 * @param token      Token
	 * @param splitValue 拼接后带前缀的值
	 */
	public void excuteExpireToken(String token, String splitValue) {
		try {
			stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
				@SuppressWarnings({"unchecked", "rawtypes"})
				@Override
				public List<Object> execute(RedisOperations operations) throws DataAccessException {
					operations.multi();
					operations.expire(token, Constants.REDIS_EXPIRY_TIME_THIRTY_MINUTE, TimeUnit.SECONDS);
					operations.expire(splitValue, Constants.REDIS_EXPIRY_TIME_THIRTY_MINUTE, TimeUnit.SECONDS);
					return operations.exec();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

    /**
     * 事务执行登录时Token的更新
     *
     * @param oldToken
     *            之前的Token
     * @param token
     *            新Token
     * @param value
     *            Token值
     * @param valuePrefix
     *            前缀
     */
    public void excuteLoginTokenUpdate(String oldToken, String token, String value, String valuePrefix) {
        try {
            stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
                @SuppressWarnings({ "unchecked", "rawtypes" })
                @Override
                public List<Object> execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    if (StringUtils.isNotBlank(oldToken)) {// 有Token，更新以前的Token
                        operations.delete(oldToken);
                    }
                    operations.opsForValue().set(token, value);
                    operations.opsForValue().set(valuePrefix + value, token);
                    return operations.exec();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 事务执行退出登录去除Token操作
     *
     * @param token
     *            Token
     * @param tokenPrefixValue
     *            Token值-带前缀
     */
    public void excuteLogout(String token, String tokenPrefixValue) {
        try {
            stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
                @SuppressWarnings({ "unchecked", "rawtypes" })
                @Override
                public List<Object> execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    operations.delete(token);
                    operations.delete(tokenPrefixValue);
                    return operations.exec();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	public void setJson(String key, JSONObject json) {
		set(key, json.toJSONString());
	}

	public JSONObject getJson(String token) {
		String jsonStr = get(token);
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}
		return JSONArray.parseObject(jsonStr);
	}

	public Integer getIntegerJsonValue(String token, String key) {
		JSONObject json = getJson(token);
		return json.getInteger(key);
	}

	public String getStringJsonValue(String token, String key) {
		JSONObject json = getJson(token);
		return json.getString(key);
	}

}
