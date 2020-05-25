# 业务模块公共依赖的Dao层
    处理数据库连接层相关操作
    
## org.apache.ibatis.plugin.Interceptor
Mybatis3 插件采用责任链模式，通过动态代理组织多个拦截器（插件），通过这些拦截器可以改变Mybatis的默认行为（诸如SQL重写之类的）