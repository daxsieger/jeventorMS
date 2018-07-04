package io.github.jhipster.jeventor.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.jeventor.domain.Evento.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Evento.class.getName() + ".statis", jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Assistito.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Gestore.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.TipoEvento.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Produttore.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Stato.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Stato.class.getName() + ".eventis", jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Stadio.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Processo.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.jeventor.domain.Transizioni.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
