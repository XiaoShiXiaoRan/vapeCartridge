package com.fidnortech.xjx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
public class HttpUtils {


    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";




    /**
     * 获取请求HttpServletRequest
     */
    public static HttpServletRequest getRequest() {

        ServletRequestAttributes requestAttributes1 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes1.getRequest();
    }
    /**
     * 获取请求HttpServletResponse
     */

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes1 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes1.getResponse();
    }

    public static HttpServletResponse setResponse() {
        ServletRequestAttributes requestAttributes1 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes1.getResponse();
    }


    /**
     * POST请求方式，参数为JSON格式
     *
     * @param url   请求的URL
     * @param param 参数 (可为空)
     * @return
     */
    public static JSONObject doPostByJson(String url, JSONObject param) {

        JSONObject json = new JSONObject();


        RestTemplate restTemplate = new RestTemplate();

        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        //设置为JSON格式
        headers.setContentType(MediaType.APPLICATION_JSON);
        //创建请求
        HttpEntity<JSONObject> request = new HttpEntity<>(param, headers);

        ResponseEntity<String> response = null;
        String responseBody = "";
        try {
            //第一个参数，请求的url;第二个参数，http请求;第三个参数，响应的类型
            response = restTemplate.postForEntity(url, request, String.class);
            //调整响应体的编码格式
            responseBody = new String(response.getBody().getBytes("utf-8"), "utf-8");

        } catch (RestClientException clientExp) {//请求出错
            json.put("message", clientExp.getMessage());
            return json;
        } catch (UnsupportedEncodingException encodingExp) {//转码出错
            json.put("message", "结果集转码错误");
            json.put("result", false);
            return json;
        }

        //返回相应的JSON数据
        json = JSON.parseObject(responseBody);
        return json;
    }

    /**
     * Get请求方式，参数为JSON格式,内部会转为指定格式
     *
     * @param url   请求的URL
     * @param param 参数 (可为空)
     * @return
     */
    public static JSONObject doGetByJson(String url, JSONObject param) {

        if (parseURLParam(param) != null && parseURLParam(param) != "") {
            url = url + "?" + parseURLParam(param);
        }
        RestTemplate restTemplate = new RestTemplate();
        JSONObject json = new JSONObject();

        ResponseEntity<String> response = null;
        String responseBody = "";
        try {
            //第一个参数，请求的url;第二个参数，响应的类型
            response = restTemplate.getForEntity(url, String.class);
            //调整响应体的编码格式
            responseBody = new String(response.getBody().getBytes("utf-8"), "utf-8");

        } catch (RestClientException clientExp) {//请求出错
            json.put("message", clientExp.getMessage());
            return json;
        } catch (UnsupportedEncodingException encodingExp) {//转码出错
            json.put("message", "结果集转码错误");
            json.put("result", false);
            return json;
        }

        //返回相应的JSON数据
        json = JSON.parseObject(responseBody);
        return json;
    }

    /**
     * 将JSON参数转换为k1=v1&k2=v2格式
     *
     * @param params
     * @return String
     */
    private static String parseURLParam(JSONObject params) {
        StringBuilder p = new StringBuilder();
        int current = 0;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            current++;
            p.append(param.getKey() + "=" + param.getValue());
            if (current < params.entrySet().size()) {
                p.append("&");
            }
        }
        return p.toString();
    }



    /**
     * 获取请求HttpServletResponse
     */
    public static String getUrlAddress() {
        StringBuffer requestURL = HttpUtils.getRequest().getRequestURL();
        return requestURL.toString();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public static Map<String, String[]> getRequestParams() {
        Map<String, String[]> parameterMap = HttpUtils.getRequest().getParameterMap();
        return parameterMap;
    }

    /**
     * 获取请求Ip地址
     *
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request = HttpUtils.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    public static UserAgent getUserAgent() {
        HttpServletRequest request = HttpUtils.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        return userAgent;

    }

    /**
     * 判断一个 字符串是否为请求地址
     * @param url
     * @return
     */
    public static boolean urlRegex(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static InetAddress getHostAddress() throws UnknownHostException {

        InetAddress addr = null;
        addr = InetAddress.getLocalHost();
        return addr;
    }



    /**
     * 发送 get 请求
     * @param url 请求地址
     * @return 请求结果
     */
    public static String get(String url) {
        String result = null;
        CloseableHttpResponse response = null;

        HttpClientBuilder bui = HttpClients.custom();
        //https设置校验签证通过
        bui.setSSLHostnameVerifier((hostName, sslSession) -> {
            return true; // 证书校验通过
        });
        CloseableHttpClient httpclient = bui.build();
        // CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("HttpUtils请求发送错误end");
        }
        return result;
    }



    /**
     * 发送 get 请求
     * @param url 请求地址
     * @return 请求结果
     */
    public static String get(String url,String  headerKey,  String  headerValue ) {
        String result = null;
        CloseableHttpResponse response = null;

        HttpClientBuilder bui = HttpClients.custom();
        //https设置校验签证通过
        bui.setSSLHostnameVerifier((hostName, sslSession) -> {
            return true; // 证书校验通过
        });
        CloseableHttpClient httpclient = bui.build();
        // CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);


            httpGet.addHeader(headerKey, headerValue);
            //httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            //log.info("headerKey="+headerKey);
            //log.info("headerValue="+headerValue);
            // 执行请求
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.info("HttpUtils请求发送错误start");
            log.info("请求地址url=--->"+url);
            log.info("e.toString()="+e.toString());
            e.printStackTrace();
            log.info("HttpUtils请求发送错误end");

        }
        return result;
    }

    /**
     * 发送 post 请求
     *
     * @param url     请求地址
     * @param jsonStr Form表单json字符串
     * @return 请求结果
     */
    public static String post(String url, String jsonStr) {
        // 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post请求方式实例
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头 发送的是json数据格式
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        //httpPost.setHeader("Connection", "Close");

        // 设置参数---设置消息实体 也就是携带的数据
        StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
        // 设置编码格式
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        // 把请求消息实体塞进去
        httpPost.setEntity(entity);

        // 执行http的post请求
        CloseableHttpResponse httpResponse;
        String result = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取请求ip地址
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

}
