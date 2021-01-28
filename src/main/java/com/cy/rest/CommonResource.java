package com.cy.rest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.cy.common.exception.AuthTokenException;
import com.cy.common.util.ReqResUtils;
import com.cy.common.util.StringUtil;
import com.cy.common.util.gsonUtil.GsonBuilderUtil;
import com.cy.domain.Sequence;
import com.cy.service.SequenceService;
import com.cy.service.impl.RedisStringService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 公共方法
 */
public class CommonResource {

	@Autowired
	public RedisStringService redisStringService;

	@Autowired
	public SequenceService sequenceService;

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	public String getToken(HttpServletRequest request) {
		String token = ReqResUtils.getStringToDefault(request, "token", null);
		if (token == null){
			token = request.getHeader("token");
		}
		String value = redisStringService.get(token);
		if (StringUtils.isBlank(value)) {
			throw new AuthTokenException("Token认证异常");
		}
		return token;
	}

	public Integer getUserId(HttpServletRequest request) {
		String token = ReqResUtils.getStringToDefault(request, "token", null);
		if (token == null){
			token = request.getHeader("token");
		}
		String value = redisStringService.get(token);
		if (StringUtils.isBlank(value)) {
			throw new AuthTokenException("Token认证异常");
		}
		return Integer.parseInt(value);
	}

	public void criteriaEqual(Criteria criteria, String key, Object value) {
		if (value != null) {
			criteria.andEqualTo(key, value);
		}
	}

	public void criteriaEqual(Criteria criteria, String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			criteria.andEqualTo(key, value);
		}
	}

	public void criteriaLike(Criteria criteria, String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			criteria.andLike(key, concatLike(value));
		}
	}

	public String concatLike(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return "%" + value + "%";
	}


	/**
	 * String转对象类
	 * @param str
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> Object parseString2Clazz(String str, Class clazz) {
		str = StringUtil.underline2Camel(str);
		JSONObject json = JSONObject.parseObject(str);
		String jsonStr = json.toJSONString();
		Gson gson = GsonBuilderUtil.getBack();
		Object get = gson.fromJson(jsonStr, clazz);
		return get;
	}


	/**
	 * 将String字符串转为list集合
	 */
	public <T> List<T> parseString2List(String json, Class clazz) {
		Type type = new ParameterizedTypeImpl(clazz);
		List<T> list = new Gson().fromJson(json, type);
		return list;
	}


	private class ParameterizedTypeImpl implements ParameterizedType {
		Class clazz;

		public ParameterizedTypeImpl(Class clz) {
			clazz = clz;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return new Type[]{clazz};
		}

		@Override
		public Type getRawType() {
			return List.class;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}
	}


	/**
	 * 根据key和usefor获取序列code
	 * @param key
	 * @param useFor
	 * @return
	 */
	public String getSequenceCode(String key,String useFor){
		Example example = new Example(Sequence.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("key",key);
		criteria.andEqualTo("useFor",useFor);
		Sequence sequence = sequenceService.getOneByExample(example);
		String code = "";
		//如果没有序列,则新建且设置初始值为0
		if (sequence == null){
			sequence = new Sequence();
			sequence.setKey(key);
			sequence.setUseFor(useFor);
			sequence.setVal("0");
			sequenceService.insert(sequence);
			code = sequence.getVal();
		}else {
			//如果已有序列则使其加1
			Integer oldVal = Integer.valueOf(sequence.getVal());
			sequence.setVal(String.valueOf(oldVal + 1));
			sequenceService.update(sequence);
			code = sequence.getVal();
		}
		return code;
	}
}
