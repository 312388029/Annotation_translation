package com.cy.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cy.common.constants.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StringUtil {
    public static final Gson gson = new Gson();
    public static final GsonBuilder baseGsonBuilder = new GsonBuilder();
    public static final GsonBuilder lowerCaseWithUnderscoresGsonBuilder = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 字符右填充
     *
     * @param source
     * @param len
     * @param padStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String rightPad(String source, int len, String padStr) {
        if (padStr == null || padStr.equals("")) {
            padStr = "  ";
        }
        if (source == null)
            source = "";
        StringBuilder ss = new StringBuilder(source);
        int slen = 0;
        try {
            slen = source.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int dlen = padStr.length();
        while (true) {
            if (slen >= len)
                break;
            if ((len - slen) > dlen) {
                ss.append(padStr);
                slen += dlen;
            } else {
                ss.append(padStr.substring(0, len - slen));
                break;
            }
        }
        return ss.toString();
    }

    public static String rightSubPad(String source, int len, String padStr) {
        if (padStr == null || padStr.equals("")) {
            padStr = " ";
        }
        if (source == null)
            source = "";
        if (source.length() > len) {
            source = source.substring(0, len);
        }
        StringBuffer ss = new StringBuffer(source);
        int slen = 0;
        try {
            slen = source.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int dlen = padStr.length();
        while (true) {
            if (slen >= len)
                break;
            if ((len - slen) > dlen) {
                ss.append(padStr);
                slen += dlen;
            } else {
                ss.append(padStr.substring(0, len - slen));
                break;
            }
        }
        return ss.toString();
    }

    /**
     * 字符右对齐左填充
     *
     * @param source
     * @param len
     * @param padStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String leftPad(String source, int len, String padStr) {
        if (padStr == null || padStr.equals("")) {
            padStr = "  ";
        }
        if (source == null)
            source = "";
        StringBuffer ss = new StringBuffer(source);
        int slen = 0;
        try {
            slen = source.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int dlen = padStr.length();
        while (true) {
            if (slen >= len)
                break;
            if ((len - slen) > dlen) {
                ss.insert(0, padStr);
                slen += dlen;
            } else {
                ss.insert(0, padStr.substring(0, len - slen));
                break;
            }
        }
        return ss.toString();
    }

    public static String leftSubPad(String source, int len, String padStr) {
        if (padStr == null || padStr.equals("")) {
            padStr = " ";
        }
        if (source == null)
            source = "";
        if (source.length() > len) {
            source = source.substring(0, len);
        }
        StringBuffer ss = new StringBuffer(source);
        int slen = 0;
        try {
            slen = source.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int dlen = padStr.length();
        while (true) {
            if (slen >= len)
                break;
            if ((len - slen) > dlen) {
                ss.insert(0, padStr);
                slen += dlen;
            } else {
                ss.insert(0, padStr.substring(0, len - slen));
                break;
            }
        }
        return ss.toString();
    }

    /**
     * 转换为utf-8编码
     *
     * @param s
     * @return
     */
    public static String toUtf8String(String s) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断是否为空
     *
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return !(value != null && !value.equals(""));
    }

    /**
     * 处理空字符
     *
     * @param str
     * @return
     */
    public static String nullToString(Object str) {
        if (str == null) {
            return "";
        }

        return String.valueOf(str);
    }

    public static String  spaceToString(String str, String replacement) {
        if (str == null) {
            return "";
        }
        return str.replaceAll("\\s", replacement);
    }

    /**
     * @param paramMap 参数的Map
     * @return 字符串参数形式的Map
     * @Title: formatParamMapToStr
     * @Description: 将参数的Map转换成字符串的参数形式
     */
    public static String formatParamMapToStr(Map<String, String> paramMap) {
        StringBuilder paramSb = new StringBuilder();
        for (String key : paramMap.keySet()) {
            paramSb.append(key + "=" + paramMap.get(key) + "&");
        }
        return paramSb.substring(0, paramSb.length() - 1).toString();
    }

    /**
     * @param time
     * @return
     * @Title: transTime
     * @Description:将秒数转换为时分秒
     */
    public static String transTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 999)
                    return "999:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * @param amt
     * @return
     * @Title: formatDouble
     * @Description:格式化金额，保留两位小数
     */
    public static String formatMoney(double amt) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(amt);
    }

    /**
     * @param money
     * @return
     * @Title: formatDouble
     * @Description:格式化金额，保留两位小数
     */
    public static String formatMoney(String money) {
        money = StringUtils.defaultIfEmpty(money, BigDecimal.ZERO.toString());
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(Double.valueOf(money));
    }

    /**
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @Title: mapToUrlParams
     * @Description:将map转换成字符串形式,同时包含对字符进行md5进行加密
     */
    public static String mapToUrlParams(Map<String, String> map, String privateKey) throws UnsupportedEncodingException {
        StringBuilder buffer = new StringBuilder();
        String signature = getSignature(map, privateKey);
        buffer.append("sign=").append(signature);
        for (String key : map.keySet()) {
            buffer.append("&");
            buffer.append(key).append("=").append(URLEncoder.encode(map.get(key) == null ? "" : String.valueOf(map.get(key)), "utf-8"));
        }
        return buffer.toString();
    }

    /**
     * @param request
     * @return
     * @Title: checkMD5Inner
     * @Description:对内MD5加密检测
     */
    public static boolean checkMD5Inner(HttpServletRequest request) {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        Map<String, String[]> map = request.getParameterMap();
        //获取传入的参数，并将参数放入到TreeMap中(sign除外)
        for (String key : map.keySet()) {
            if ("sign".equals(key) || "datetime".equals(key)) {
                continue;
            }
            String[] values = map.get(key);
            String logValue = null;
            for (int i = 0; i < values.length; i++) {
                if (logValue == null) {
                    logValue = values[i];
                } else {
                    logValue = logValue + "," + values[i];
                }
            }
            treeMap.put(key, logValue);
        }
        String[] signs = map.get("sign");
        String sign = "";
        if (signs != null && signs.length > 0) {
            for (int i = 0; i < signs.length; i++) {
                sign += signs[i];
            }
        }
        logger.info("sign1:" + sign);
        String signature = "";
        for (String key : treeMap.keySet()) {
            signature += String.valueOf(treeMap.get(key));
        }
        logger.info("sign:" + signature);
        logger.info("MD5:" + DigestUtils.md5Hex(signature));
        return sign.equals(DigestUtils.md5Hex(signature));
    }

    /**
     * @param request
     * @return
     * @Title: checkMD5Inner
     * @Description:对内MD5加密检测
     */
    public static boolean checkMD5private(HttpServletRequest request, String privateKey) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Map<String, String[]> map = request.getParameterMap();

        //获取传入的参数，并将参数放入到TreeMap中(sign除外)
        for (String key : map.keySet()) {
            if ("sign".equals(key) || "datetime".equals(key)) {
                continue;
            }
            String[] values = map.get(key);
            String logValue = null;
            for (int i = 0; i < values.length; i++) {
                if (logValue == null) {
                    logValue = values[i];
                } else {
                    logValue = logValue + "," + values[i];
                }
            }
            treeMap.put(key, logValue);
        }
        String[] signs = map.get("sign");
        String sign = "";
        if (signs != null && signs.length > 0) {
            for (int i = 0; i < signs.length; i++) {
                sign += signs[i];
            }
        }
        logger.info("sign:" + sign);
        String signature = "";
        for (String key : sortMapByKey(treeMap).keySet()) {
            signature += String.valueOf(treeMap.get(key));
        }
        logger.info("signature:" + signature);
        logger.info("MD5:" + DigestUtils.md5Hex(signature));
        return sign.equals(DigestUtils.md5Hex(signature + privateKey));
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (map.size() == 1) {
            return map;
        }
        //map的Size为1时会出现异常
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * @param @param  name
     * @param @param  pre
     * @param @param  length
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getValue
     * @Description: 获取指定长度指定前缀序列号
     */
    public static synchronized String getValue(Integer id, int length) {

        int prelen = id.toString().length();
        if (id != null && id.intValue() != 0) {
            return id.toString() + String.format("%0" + (length - prelen) + "d", id);
        }
        return null;
    }

    /**
     * 根据key获取签名
     *
     * @param map
     * @param privateKey
     * @return
     */
    public static String getSignature(Map<String, String> map, String privateKey) {
        String signature = "";
        for (String key : sortMapByKey(map).keySet()) {
            signature += map.get(key) == null ? "" : map.get(key);
        }
        return DigestUtils.md5Hex(signature + privateKey);
    }

    /**
     * 将MultiValueMap的key排序拼接后再加私钥进行md5加密
     *
     * @param map
     * @param privateKey
     * @return
     */
    public static String getSignature(MultiValueMap<String, String> map, String privateKey) {
        final String[] signature = {""};
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(j ->
                signature[0] += j.getValue().get(0) == null ? "" : j.getValue().get(0));
        return DigestUtils.md5Hex(signature[0] + privateKey);
    }

    /**
     * 给传入的查询条件创建签名
     * @param queryString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateSignatureQueryString(String queryString) throws UnsupportedEncodingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String privateKey = "";
        String parseStringQuery = "";
        for (String kv : queryString.split("&")) {
            String [] param = kv.split("=");
            if (param.length >= 2) {
//                    logger.debug("Key {}, Value {}", param[0], URLDecoder.decode(param[1], "UTF-8"));
                if (param[0].equals("privateKey")) {
                    privateKey = param[1];
                } else {
                    params.add(param[0], URLDecoder.decode(param[1], "UTF-8"));
                    if (StringUtils.isEmpty(parseStringQuery)) {
                        parseStringQuery += param[0] + "=" + param[1];
                    } else {
                        parseStringQuery += "&" + param[0] + "=" + param[1];
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(privateKey)) {
            String sign = StringUtil.getSignature(params, privateKey);
            parseStringQuery += "&sign=" + sign;
        }
        return parseStringQuery;
    }

    public static String generateSignatureQueryString(String queryString, boolean isDecoder) throws UnsupportedEncodingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String privateKey = "";
        String parseStringQuery = "";
        for (String kv : queryString.split("&")) {
            String[] param = kv.split("=");
            if (param.length >= 2) {
//                    logger.debug("Key {}, Value {}", param[0], URLDecoder.decode(param[1], "UTF-8"));
                if (param[0].equals("privateKey")) {
                    privateKey = param[1];
                } else if (param[0].equals("privatePhoneKey")) {
                    privateKey = param[1];
                } else {
                    if (isDecoder) {
                        params.add(param[0], param[1]);
                    } else {
                        params.add(param[0], URLDecoder.decode(param[1], "UTF-8"));
                    }
                    if (StringUtils.isEmpty(parseStringQuery)) {
                        parseStringQuery += param[0] + "=" + param[1];
                    } else {
                        parseStringQuery += "&" + param[0] + "=" + param[1];
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(privateKey)) {
            String sign = StringUtil.getSignature(params, privateKey);
            parseStringQuery += "&sign=" + sign;
        }
        return parseStringQuery;
    }
    /**
     * 给传入的查询条件创建签名
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateSignatureQueryString(MultiValueMap<String, String> params, String privateKey, boolean isEncode) throws UnsupportedEncodingException {
        String sign = "";
        for (String key : params.keySet()) {
            params.get(key).add(0, URLEncoder.encode(params.get(key).get(0), "UTF-8"));
        }
        if (!StringUtils.isEmpty(privateKey)) {
            sign = StringUtil.getSignature(params, privateKey);
        }
        return sign;
    }

    /**
     * 时间转换
     * @author wangwen
     * @param date 时间
     * @return 刚刚（半小时内）、1小时前、2小时前（2小时以上的都为2小时前）
     */
    public static String timeConvert(Date date) {
        if(date == null)  return "";

        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(curDate);
        calendar.add(Calendar.HOUR, -2);
        Date date2 = calendar.getTime();

        if (date.before(date2)) {
            return "2小时前";
        }

        calendar.setTime(curDate);
        calendar.add(Calendar.MINUTE, -30);
        Date date1 = calendar.getTime();
        if(date.before(date1)){
            return "1小时前";
        }

        return "刚刚";
    }

    /**
     * 名字转换
     * @author wangwen
     * @param name 姓名
     * @return 转换名字
     */
    public static String nameConvert(String name) {
        if(StringUtil.isBlank(name)){
            return "";
        }

        return name.length() > 0 ? name.substring(0, 1) + "XX" : "";
    }

    /**
     * 将文件转成base64
     *
     * @param file
     * @return
     */
    public static String getBase64ByFile(File file) {
        return getBase64ByFile(file.getPath());
    }

    /**
     * 将文件转成base64
     *
     * @param filePath
     * @return
     */
    public static String getBase64ByFile(String filePath) {
        try {
            File localFile = new File(filePath);
            byte[] fileBytes = FileUtils.readFileToByteArray(localFile);
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 一次性token 86400秒不重复 16位
     *
     * @return token
     */
    public static String getNonce() {
        String now = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return now + generateShortUuid();
    }

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String generateShortUuid() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * @Description:生成格式为: 年月日时分秒14位+毫秒3位+短UUID8位
     * @return orderId 订单编号
     */
    public static String genOrderId() {
        return DateUtils.getDate("yyyyMMddHHmmssSSS") + generateShortUuid();
    }

    public static String getFileName() {
        return DateUtils.getDate("yyyyMMddHHmmssSSS") + getNonce();
    }

    /**
     * 时间戳
     *
     * @return
     */
    public static String getTimestamp() {
        return DateUtils.getDate("yyyyMMddHHmmssSSS");
    }

    @SuppressWarnings("rawtypes")
    public static MultiValueMap transferObjToMultiValueMap(Object obj) {
        if(null == obj){
            return new LinkedMultiValueMap<>();
        }
		HashMap hashMap = gson.fromJson(gson.toJson(obj), HashMap.class);
        return transferHashMapToMultiValueMap(hashMap);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static MultiValueMap transferHashMapToMultiValueMap(HashMap hashMap) {
        MultiValueMap requestMap = new LinkedMultiValueMap<>();
        if(CollectionUtils.isEmpty(hashMap)){
            return requestMap;
        }
        hashMap.keySet().stream().forEach(key ->{
            requestMap.add(key, hashMap.get(key));
        });
        return requestMap;
    }

    /**
     * 下划线转驼峰
     *
     * @param underlineStr
     * @return
     */
    public static String underline2Camel(String underlineStr) {

        if (StringUtils.isEmpty(underlineStr)) {

            return StringUtils.EMPTY;
        }

        int len = underlineStr.length();
        StringBuilder strb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {

            char c = underlineStr.charAt(i);
            if (c == Constants.UNDERLINE_CHAR && (++i) < len) {

                c = underlineStr.charAt(i);
                strb.append(Character.toUpperCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }

}
