package com.fidnortech.xjx.common;

/**
 * @author pansicong
 * @date 2022/6/13
 **/
public class CommonUtil {
    // 重要提示！！！！： 如果要加入自己的项目工程，这里填写的参数请一定写成配置模式，不要写成固定模式，不然代码安全扫描会异常

    /**
     * 合作方申请的appId
     * 这里 不是真实的app_id，请向相关部门人员申请后获取，并填写在这里 */
    public static final String APP_ID = "8778150194";

    /**
      合作方申请的appSecret
     * 这里 不是真实的app_Secret，请向相关部门人员申请后获取，并填写在这里 */
    public static final String APP_SECRET = "46518ebe0bce491db5cf0414cfa8e963";

//    /**  合作方申请手机号码，请自己填写这里处理 */
     public static final String Phone = "17336981860";

    /**
     * 服务端版本号，如v1.0  通常固定是v1.0
     */
    public static final String VERSION = "v1.0";

    /**
     * 端类型,如: 1、apple 2.android 3 Pc
     */
    public static final String CLINT_TYPE = "3";

    /** 合作方自己生成的RSA加密秘钥中，用于本地解密的 本地私钥,这里不是真实的，请自己生成后填写这里。*/

    /** 公钥 */
   public static final String publicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDN2tD44K+CP6GyE82dhM3e8GdyimXqEBQ9bl+UwoK8lyPWVDoIjdEnafM9O7GxtBSLsnuXHXXjnhEfsILdVHgpzo9/1cxVfFNMbrDrjddDdmuuJhWV6ncuHs0avd9GjCCmVYIJ0krP5luHdghjp8z2HPbRURAmoEy/4rc4poQEXwIDAQAB";

    /** 私钥 */
   public static final String privateKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM3a0Pjgr4I/obITzZ2Ezd7wZ3KKZeoQFD1uX5TCgryXI9ZUOgiN0Sdp8z07sbG0FIuye5cddeOeER+wgt1UeCnOj3/VzFV8U0xusOuN10N2a64mFZXqdy4ezRq930aMIKZVggnSSs/mW4d2CGOnzPYc9tFRECagTL/itzimhARfAgMBAAECgYB2K1dsluaJWWfD5vRQqSzY/p4TtXjdjU9hGFwL9EDCa+ypm34Y7PgUrRBxpTPTM/HbY6Do1YOYH2PrEhN9ZID5TkabibZxmkeJ315tx1a58RwcMJdcopNuX2KIFGRuKSe9rk7I+HVs7R8YKUG0s8kkEjALLvvA3PP9Q5APyJea+QJBAPp+ezLTz/m3qEbNJNPBbeq+TB8cb2+Nx9cHm8tsWN4diBeoMPneCha8tj7Me12jL5xQP84GqPTpA8m5Cei6tesCQQDSYSbSQAJ5SNjoFHaFd8QBEmBa9cRPGkA6y6hlI4w3/DLoPNxJdIqC1D7z8y+Ll28s6vy7xnH0Kr5rzBV9zkpdAkBqm7HrK+JzsQpZN63YwZX6NgtHol5656iJh59ysCYSQhdNmjNd5HX+v0oA6B4I+A6nRDAjiwoVUaZfD/V7qxsJAkASURcaMyyD9s271xxCnpzYIwt0n/VJldWFyPmOZLvum+yqv7HGS3lLzsL0ZnX7R2IMnSEIEE0XnykZJZmxbGaZAkBWUZuKKBR4GGFwnnxLgWUXh9rrPYK51EEXKqqWrxzVTE73u5MOnelDCC34NCrxZQB9SRjJfG3HDxqHq2yiTTYm";

}
