package com.social.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

/**
 * Created by mithundas on 11/28/14.
 */
@Configuration
@EnableSocial
public class SpringSocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//        cfConfig.addConnectionFactory(new TwitterConnectionFactory(
//                env.getProperty("twitter.consumer.key"),
//                env.getProperty("twitter.consumer.secret")
//        ));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")
        ));
        cfConfig.addConnectionFactory(new LinkedInConnectionFactory(
                env.getProperty("linkedin.app.id"),
                env.getProperty("linkedin.app.secret")
        ));
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                env.getProperty("google.client.id"),
                env.getProperty("google.client.secret")
        ));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
