package com.dilek.alptug.hazelcastaws.configuration;

import com.hazelcast.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.sg.enabled}")
    private boolean sgEnabled;

    @Value("${app.tag.enabled}")
    private boolean tagEnabled;

    @Value("${app.sg.value}")
    private String sgValue;

    @Value("${app.tag.key}")
    private String tagKey;

    @Value("${app.tag.value}")
    private String tagValue;

    @Bean
    public Config hazelCastConfig(){
        Config config = new Config();

        logger.info("Configuring with sg filter enabled: {} and tag filter enabled: {}", sgEnabled, tagEnabled);

        if (sgEnabled || tagEnabled) {
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(true);
        }

        if (sgEnabled) {
            logger.info("Configuring with security-group-name {}", sgValue);
            config.getNetworkConfig().getJoin().getAwsConfig().setProperty("security-group-name", sgValue);
        } else if (tagEnabled) {
            logger.info("Configuring with tag-key {} and tag value {}", tagKey, tagValue);
            config.getNetworkConfig().getJoin().getAwsConfig().setProperty("tag-key", tagKey).setProperty("tag-value", tagValue);
        }

        return config;
    }
}
