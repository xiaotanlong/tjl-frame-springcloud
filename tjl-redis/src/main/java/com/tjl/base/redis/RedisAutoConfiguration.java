
package com.tjl.base.redis;

import com.tjl.base.redis.serializer.FastJson2JsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.List;

@Configuration //开启配置
@EnableConfigurationProperties(RedisProperties.class) //开启使用映射实体对象
public class RedisAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);
    //单机
    public static final String REDIS_STANDALONE = "standalone";
    //哨兵
    public static final String REDIS_SENTINEL = "sentinel";

    @Autowired
    private RedisProperties jedisConfig;

    /*@Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties){
        Jedis jedis = new Jedis(redisProperties.getHost(), redisProperties.getPort());
        return jedis;
    }*/


    @Bean
    public CacheManager init(@Qualifier("connectionFactory") JedisConnectionFactory jedisConnectionFactory) {
        RedisCacheManager cacheManager = RedisCacheManager.builder(jedisConnectionFactory).build();
        cacheManager.initializeCaches();
        return cacheManager;
    }

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer(Object.class);
    }

    @Bean(name = {"redisTemplate"})
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> initRedisTemplate(@Qualifier("connectionFactory") JedisConnectionFactory jedisConnectionFactory, RedisSerializer fastJson2JsonRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * 功能描述:连接工厂配置
     * @return JedisConnectionFactory
     */
    @Bean(name = {"connectionFactory"})
    public JedisConnectionFactory jedisConnectionFactory() {
        String mode = this.jedisConfig.getMode();
        if (mode.equals(REDIS_STANDALONE)) {
            RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
            rf.setDatabase(this.jedisConfig.getDatabase());
            rf.setHostName(this.jedisConfig.getHost());
            rf.setPort(this.jedisConfig.getPort());
            if (!StringUtils.isEmpty(this.jedisConfig.getPassword())) {
                rf.setPassword(RedisPassword.of(this.jedisConfig.getPassword()));
            }
            JedisPoolingClientConfigurationBuilder jpb = (JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
            JedisPoolConfig jedisPoolConfig = this.createJedisPoolConfig();
            jpb.poolConfig(jedisPoolConfig);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
            return jedisConnectionFactory;
        } else if (!mode.equals(REDIS_SENTINEL)) {
            return null;
        } else {
            List<String> redisNodes = this.jedisConfig.getSentinel().getNodes();
            String master = this.jedisConfig.getSentinel().getMaster();
            RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
            Iterator var5 = redisNodes.iterator();
            while(var5.hasNext()) {
                String redisHost = (String)var5.next();
                String[] item = redisHost.split(":");
                String ip = item[0];
                String port = item[1];
                configuration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
            }
            configuration.setMaster(master);
            if (!StringUtils.isEmpty(this.jedisConfig.getPassword())) {
                configuration.setPassword(RedisPassword.of(this.jedisConfig.getPassword()));
            }
            JedisPoolingClientConfigurationBuilder jpb = (JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
            JedisPoolConfig jedisPoolConfig = this.createJedisPoolConfig();
            jpb.poolConfig(jedisPoolConfig);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration, jpb.build());
            return jedisConnectionFactory;
        }
    }

    /**
     * 功能描述：设置连接池属性
     * @return edisPoolConfig
     */
    private JedisPoolConfig createJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.jedisConfig.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMinIdle(this.jedisConfig.getJedis().getPool().getMinIdle());
        jedisPoolConfig.setMaxTotal(this.jedisConfig.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(this.jedisConfig.getJedis().getPool().getMaxWait().toMillis());
        return jedisPoolConfig;
    }
}