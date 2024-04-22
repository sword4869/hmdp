package com.hmdp.utils;

public class RedisConstants {
    // 对应手机号的验证码，保存 2分钟
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;

    // 当前登录的用户 token， 保存 60 分钟
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 60L;


    // 店铺缓存信息，30分钟
    public static final String CACHE_SHOP_KEY = "cache:shop:";
    public static final Long CACHE_SHOP_TTL = 30L;
    // 店铺缓存空值，2分钟
    public static final Long CACHE_SHOP_NULL_TTL = 2L;
    // 店铺缓存击穿的互斥锁，10秒
    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;
    // 店铺缓存击穿的逻辑过期时间，20秒
    public static final Long CACHE_SHOP_EXPIRE_TIME = 20L;

    // 所有店铺类型的缓存, 30分钟
    public static final String CACHE_SHOP_TYPE_LIST_KEY = "cache:shopType";
    public static final Long CACHE_SHOP_TYPE_LIST_TTL = 30L;
    // 店铺缓存空值，2分钟
    public static final Long CACHE_SHOP_TYPE_LIST_NULL_TTL = 2L;
    // 所有店铺类型的缓存击穿的互斥锁，10秒
    public static final String LOCK_SHOP_TYPE_KEY = "lock:shopType";
    public static final Long LOCK_SHOP_TYPE_TTL = 10L;

    // 秒杀券，
    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String FEED_KEY = "feed:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";
}
