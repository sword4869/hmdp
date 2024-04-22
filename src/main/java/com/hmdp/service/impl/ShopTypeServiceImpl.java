package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 店铺类型缓存。
     *
     * 没用 CacheClient，因为这需要 JsonUtil.toList。
     * @return
     */
    @Override
    public List<ShopType> queryShopTypes() {
        // 1.从 Redis 中查询商铺缓存
        String shopTypeJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_TYPE_LIST_KEY);

        // 2.判断 Redis 中是否存在数据
        if (StrUtil.isNotBlank(shopTypeJson)) {
            // 2.1.存在，则返回
            return JSONUtil.toList(shopTypeJson, ShopType.class);
        }
        // 空值
        else if(shopTypeJson != null){
            return null;
        }

        List<ShopType> shopTypes = null;
        try{
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_SHOP_TYPE_KEY, "1", LOCK_SHOP_TYPE_TTL, TimeUnit.SECONDS);
            boolean isLock = BooleanUtil.isTrue(flag);
            // 获取锁失败
            if(!isLock){
                Thread.sleep(50);
                return queryShopTypes();
            }

            // double check
            shopTypeJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_TYPE_LIST_KEY);
            if (StrUtil.isNotBlank(shopTypeJson)) {
                // 2.1.存在，则返回
                return JSONUtil.toList(shopTypeJson, ShopType.class);
            }
            else if(shopTypeJson != null){
                return null;
            }

            // 查db
            shopTypes = query().orderByAsc("sort").list();
            // 空值
            if (shopTypes == null) {
                // 3.1.数据库中也不存在
                stringRedisTemplate.opsForValue().set(CACHE_SHOP_TYPE_LIST_KEY, "", CACHE_SHOP_TYPE_LIST_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            // 数据库中存在，则将查询到的信息存入 Redis
            stringRedisTemplate.opsForValue().set(CACHE_SHOP_TYPE_LIST_KEY, JSONUtil.toJsonStr(shopTypes), CACHE_SHOP_TYPE_LIST_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stringRedisTemplate.delete(LOCK_SHOP_TYPE_KEY);
        }
        return shopTypes;
    }

}
