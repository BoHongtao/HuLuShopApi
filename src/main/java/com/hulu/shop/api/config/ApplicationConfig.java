/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.api.config;

import com.google.gson.Gson;
import com.hulu.shop.api.controller.user.UserController;
import com.hulu.shop.api.utils.HttpJsonMessageConverter;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
//import io.ebean.spring.txn.SpringJdbcTransactionManager;

import io.ebean.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableTransactionManagement
public class ApplicationConfig implements WebMvcConfigurer {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public HttpJsonMessageConverter converter() {
        return new HttpJsonMessageConverter();
    }

    @Bean
    public EbeanServer getEbeanServer(DataSource dataSource) {
        ServerConfig config = new ServerConfig();
        config.setDataSource(dataSource);
//        config.setExternalTransactionManager(new SpringJdbcTransactionManager());

        config.loadFromProperties();
        config.setDefaultServer(true);
        config.setRegister(true);

        return EbeanServerFactory.create(config);
    }
}
