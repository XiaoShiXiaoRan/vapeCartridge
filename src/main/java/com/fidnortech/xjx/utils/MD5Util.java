package com.fidnortech.xjx.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md2Hex(String src) {
        return DigestUtils.md5Hex(src);
    }
}
