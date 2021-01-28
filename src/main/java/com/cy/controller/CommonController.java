package com.cy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cy.api.vm.ResponseJsonVM;
import com.cy.common.util.ReqResUtils;
import com.cy.common.util.SecretUtil;
import com.cy.service.impl.RedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {
	@Autowired
	private RedisStringService   redisStringService;

	@RequestMapping(value = "/404")
	public void notFount(HttpServletRequest request, HttpServletResponse response) {
		ReqResUtils.printHtml(response, "<h1>404</h1>");
	}

	@RequestMapping(value = "/500")
	public void error(HttpServletRequest request, HttpServletResponse response) {
		ReqResUtils.printHtml(response, "<h1>500</h1>");
	}

	@RequestMapping(value = "/add/{userId}")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response, @PathVariable String userId) {
		JSONObject json = new JSONObject();
		json.put("userId", userId);
		String token = SecretUtil.getMd5Pwd(userId, SecretUtil.createUUID());
		redisStringService.setJson(token, json);
		return ResponseJsonVM.success(token);
	}
}
