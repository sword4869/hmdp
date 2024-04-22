## log

【用户登录】
- 注释调整、删除session冗余代码
- 调整redis保存的User的过期时间为60分钟。
- 从yaml配置文件读取redis连接 [RedissonConfig.java](src/main/java/com/hmdp/config/RedissonConfig.java)
- 优化logout 漏洞 [logout](src/main/java/com/hmdp/service/impl/UserServiceImpl.java)

【店铺信息和店铺类型】
- 优化店铺信息的缓存击穿：互斥锁方式和逻辑过期方式，都用 double check
- 完成店铺类型缓存 [queryShopTypes](src/main/java/com/hmdp/service/impl/ShopTypeServiceImpl.java)
- [ ] queryShopByType 怎么都是去db查？redis中的消息在哪

## 主要优化
- [x] 从yaml配置文件读取redis连接 [RedissonConfig.java](src/main/java/com/hmdp/config/RedissonConfig.java)
- [x] 优化logout 漏洞 [logout](src/main/java/com/hmdp/service/impl/UserServiceImpl.java)