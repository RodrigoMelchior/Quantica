package br.com.web.pesquisas.configuration;
import java.util.SortedSet;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.codahale.metrics.MetricRegistry;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class, DatabaseConfiguration.class })
@Slf4j
public class CacheConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private MetricRegistry metricRegistry;

    private net.sf.ehcache.CacheManager cacheManager;

    @PreDestroy
    public void destroy() {
        log.info("Remove Cache Manager metrics");
        SortedSet<String> names = metricRegistry.getNames();
        names.forEach(metricRegistry::remove);
        log.info("Closing Cache Manager");
        cacheManager.shutdown();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
      RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
      redisTemplate.setConnectionFactory(cf);
      return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
      RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

      // Number of seconds before expiration. Defaults to unlimited (0)
      cacheManager.setDefaultExpiration(300);
      return cacheManager;
    }
}
