package com.bee.master.configuration;

import com.bee.master.adapter.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class MockConfiguration {

    @Primary
    @Bean
    public UserClient userClient(){
        return mock(UserClient.class);
    }
}
