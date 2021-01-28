package com.cy.api.vm;

import com.google.gson.Gson;
import com.cy.common.constants.Constants;
import com.cy.common.util.gsonUtil.GsonBuilderUtil;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResponseJsonVM {
    @ApiModelProperty(value = "返回码", required = true)
    private String code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public static String success() {
        return successMap(null);
    }

    public static String success(Object data) {
        Map map = new HashMap();
        map.put("code", Constants.SUCCEED);
        map.put("msg", "success");
        map.put("data", data);
        Gson gson = GsonBuilderUtil.toUnder();
        String jsonMap = gson.toJson(map);
        return jsonMap;
    }


    public static String successMsg(String msg) {
        return success(Constants.SUCCEED, msg, null);
    }

    private static String success(String code, String msg, Object data) {
        Map map = new HashMap(3);
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        Gson gson = GsonBuilderUtil.toUnder();
        String jsonMap = gson.toJson(map);
        return jsonMap;
    }

    public static String successMap(HashMap datamap) {
        Gson gson = GsonBuilderUtil.toUnder();
        Map map = new HashMap();
        map.put("code", Constants.SUCCEED);
        map.put("msg", "success");
        try {
            Iterator iter = datamap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }catch (Exception e){
            String jsonMap = gson.toJson(map);
            return jsonMap;
        }
        String jsonMap = gson.toJson(map);
        return jsonMap;
    }

//	public static ResponseJsonVM success(Object data) {
//		ResponseJsonVM vm = new ResponseJsonVM();
//		vm.setCode(Constants.SUCCEED);
//		vm.setMsg("success");
//		// TODO 使用Gson对返回的data对象做驼峰转下划线处理
//		String jsonStr = GsonBuilderUtil.toUnder().toJson(data);
//		vm.setData(JSONObject.parse(jsonStr));
//		return vm;
//	}


    public static String fail(String msg) {
        return fail(Constants.FAIL, msg, null);
    }

    public static String fail(String code, String msg) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        Gson gson = GsonBuilderUtil.toUnder();
        String jsonMap = gson.toJson(map);
        return jsonMap;
    }

    public static String fail(String code, String msg, HashMap datamap) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        if (datamap != null && !datamap.isEmpty()){
            Iterator iter = datamap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }
        Gson gson = GsonBuilderUtil.toUnder();
        String jsonMap = gson.toJson(map);
        return jsonMap;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
