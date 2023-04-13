package com.gaaji.auth.event;


import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {


    @Autowired
    ApplicationContext applicationContext;


    @Bean
    public InitializingBean initializingBean(){
        return () -> Events.setApplicationEventPublisher(applicationContext);

    }

}
