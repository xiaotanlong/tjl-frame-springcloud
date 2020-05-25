### redis工具封装2020-01-05
相关术语：哨兵 集群 结合mybatis自动更新缓存 分布式锁 击穿/雪崩  序列化


 * 这些是springboot特有的，常见的条件依赖注解有：
 * @ConditionalOnBean，仅在当前上下文中存在某个bean时，才会实例化这个Bean。
 * @ConditionalOnClass，某个class位于类路径上，才会实例化这个Bean。
 * @ConditionalOnExpression，当表达式为true的时候，才会实例化这个Bean。
 * @ConditionalOnMissingBean，仅在当前上下文中不存在某个bean时，才会实例化这个Bean。
 * @ConditionalOnMissingClass，某个class在类路径上不存在的时候，才会实例化这个Bean。
 * @ConditionalOnNotWebApplication，不是web应用时才会实例化这个Bean。
 * @AutoConfigureAfter，在某个bean完成自动配置后实例化这个bean。
 * @AutoConfigureBefore，在某个bean完成自动配置前实例化这个bean。
