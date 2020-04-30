# Instances currently registered with Eureka 
API-GETAWAY	n/a (1)	(1)	UP (1) - localhost:api-getaway:5001 - localhost:api-getaway:5001

localhost--是hostname  本地计算机名


# SpringBoot 安全组件  设置访问 Eureka注册中心 需要用户名密码限制
```
    <!--添加安全认证-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    添加配置
    spring.security.user.name
    用户名，默认值为user，配置Spring Security内置使用内存方式存储的用户名。
    spring.security.user.password
    用户对应的密码，默认值为UUID随机字符串，配置Spring Security默认对应user用户的密码，该密码在系统启动时会在控制台打印，如果使用默认值可以运行查看控制台的输出内容。
    
```


# Eureka Clinent注册机制是怎样的
    * 1.获取注册中心配置列表（localhost:5001;localhost:5002;localhost:5003;localhost:5004）
    * 2.客户端循环列表依次向一个eureka中心发送注册请求
        + 这里有两种：启动时注册和定时任务注册
        + 只要一个服务端请求成功在终止循环
        + 请求失败的会放入quarantineSet集合中
    * 3.在请求过程中会有一个机制来规定注册请求的节点和顺序
        + 默认按配置的eureka中心地址的顺序
        + 初次（一般是启动时）返回全量的eureka中心地址
        + 后面每次请求会有一个一判断-涉及一个阀值 threshold（该值后面解析）
            - 当quarantineSet.size() >= threshold -> quarantineSet.clear(); 然后返回全量的 返回全量的eureka中心地址
            - 如果 quarantineSet.size() > threshold 则返回重全量中去除之前放在quarantineSet 中的
        + threshold = 配置的eureka中心地址个数 / 配置的系数（默认0.66）
    * 4.这个机制，其根本目的是为了让Eureka Client请求成功的概率更大
    * 5.因为心跳会一直进行  记录和缓存一部分异常注册中心可以增加 请求成功率；同时缓存到一定个数在进行清空 可以重新获取所有节点的状态
    * eureka.client.transport.retryableClientQuarantineRefreshPercentage = xxx   配置项
    * eureka.client.service-url.defaultZone=http://localhost:5001/eureka,http://localhost:5002/eureka,http://localhost:5003/eureka,http://localhost:5004/eureka
    
    
# # com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
搭建的SpringCloud的Eureka添加了安全验证，但是其他的服务注册不上了。上网查了一下，原来是Spring Security启用csrf，然后会对URL请求进行token验证，
如果请求中没有token,浏览器会任务是非法网页的请求，然后就会拒绝。
新版（Spring Cloud 2.0 以上）的security默认启用了csrf检验，要在eurekaServer端配置security的csrf检验为false;
```$xslt
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
```
在页面登录管理和客户端注册上 据说后面版本会解决兼容





