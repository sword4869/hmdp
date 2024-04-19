## log

2024/04/19: 【用户登录】
- 注释调整、删除session冗余代码
- 调整redis保存的User的过期时间为60分钟。
- 从yaml配置文件读取redis连接 [RedissonConfig.java](src/main/java/com/hmdp/config/RedissonConfig.java)
- 优化logout 漏洞 [logout](src/main/java/com/hmdp/service/impl/UserServiceImpl.java)

## 主要优化
- [x] 从yaml配置文件读取redis连接 [RedissonConfig.java](src/main/java/com/hmdp/config/RedissonConfig.java)
- [x] 优化logout 漏洞 [logout](src/main/java/com/hmdp/service/impl/UserServiceImpl.java)