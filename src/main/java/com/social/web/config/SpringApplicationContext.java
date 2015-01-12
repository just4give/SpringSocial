package com.social.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by mithundas on 11/28/14.
 */

@Configuration
@ComponentScan(basePackages = {
        "com.social.web.config",
        "com.social.web.controller",
        "com.social.middleware.service"
})
@Import({SpringPersistenceConfig.class, SpringSecurityConfig.class, SpringMVCConfig.class, SpringSocialConfig.class})
@PropertySource("classpath:application.properties")
public class SpringApplicationContext {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
