package com.deepglint.api.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-15 23:43
 **/
@Configuration
public class RedisConfig {


    /**
     * 往容器中添加RedisTemplate对象，设置序列化方式
     * @param redisConnectionFactory
     * @return
     */
    @Bean(value = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(valueSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(valueSerializer());
//		template.afterPropertiesSet();
        return template;
    }


    /**
     * 使用Jackson序列化器
     * @return
     */
    private RedisSerializer<?> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /**
         * 这一句必须要，作用是序列化时将对象全类名一起保存下来
         * 设置之后的序列化结果如下：
         *  [
         *   "com.dxy.cache.pojo.Dept",
         *   {
         *     "pid": 1,
         *     "code": "11",
         *     "name": "财务部1"
         *   }
         * ]
         *
         * 不设置的话，序列化结果如下，将无法反序列化
         *
         *  {
         *     "pid": 1,
         *     "code": "11",
         *     "name": "财务部1"
         *   }
         */
//                objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //因为上面那句代码已经被标记成作废，因此用下面这个方法代替，仅仅测试了一下，不知道是否完全正确
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }


//	/**
//	 * 往容器中添加RedisCacheManager容器，并设置序列化方式
//	 * @param redisConnectionFactory
//	 * @return
//	 */
//	@Bean
//	public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
//		redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
//		return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
//	}

//    private final CacheProperties cacheProperties;
//
//    MyRedisConfig(CacheProperties cacheProperties) {
//              this.cacheProperties = cacheProperties;
//    }

    /**
     * 往容器中添加org.springframework.data.redis.cache.RedisCacheConfiguration 对象
     * 目的是为了向默认的RedisCacheManager中设置属性，当然包括序列化
     * 如果仅仅是为了设置序列化方式可以和上面的配置二选一
     * 在RedisCacheManager内部使用org.springframework.data.redis.cache.RedisCacheConfiguration去保存相关配置信息
     */
//     @Bean
//     public org.springframework.data.redis.cache.RedisCacheConfiguration determineConfiguration() {
//               CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
//             org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration
//                     .defaultCacheConfig();
//              config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
//                                .fromSerializer(valueSerializer()));
//                if (redisProperties.getTimeToLive() != null) {
//                     config = config.entryTtl(redisProperties.getTimeToLive());
//                }
//                if (redisProperties.getKeyPrefix() != null) {
//                       config = config.prefixKeysWith(redisProperties.getKeyPrefix());
//                }if (!redisProperties.isCacheNullValues()) {
//                        config = config.disableCachingNullValues();
//                   }
//                if (!redisProperties.isUseKeyPrefix()) {
//                         config = config.disableKeyPrefix();
//                    }
//               return config;
//           }




}

