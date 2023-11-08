package com.fidnortech.xjx.utils;


import com.fidnortech.xjx.common.CommonUtil;

import java.util.*;

/**
 * 生成参数
 * @author cbx
 * @date 2020/7/3
 **/
public class GenReqParam {

    /**
     * 生成参数
     * @param specialParam 接口具体入参
     * @return
     */
    public static Map<String,Object> apiGen(Map<String, Object> specialParam) throws Exception{
        // 公共参数
        String appId = CommonUtil.APP_ID;
        String version = CommonUtil.VERSION;
        String clientType = CommonUtil.CLINT_TYPE;
        Long timestamp =  System.currentTimeMillis();

        specialParam.put("appId", appId);
        specialParam.put("version", version);
        specialParam.put("clientType", clientType);
        specialParam.put("timestamp", timestamp);

        StringBuilder sb = new StringBuilder();
        Set<String> keySet = specialParam.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = specialParam.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        String result = "";
        if(sb.length() > 0) {
             result = sb.substring(0, sb.length() - 1);
        }

        String params = XXTea.encrypt(result,"UTF-8",ByteFormat.toHex(CommonUtil.APP_SECRET.getBytes()));
        String signature = HmacSHATool.encodeHmacSHA256(appId+clientType+params+timestamp+version,CommonUtil.APP_SECRET);


        // 返回全部参数
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("appId", appId);
        resultMap.put("version", version);
        resultMap.put("clientType", clientType);
        resultMap.put("timestamp", timestamp);
        resultMap.put("signature", signature);
        resultMap.put("params", params);
        return resultMap;
    }
}
