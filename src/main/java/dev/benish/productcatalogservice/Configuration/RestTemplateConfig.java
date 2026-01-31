package dev.benish.productcatalogservice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private RestTemplate restTemplate;

    @Bean
    public  RestTemplate createResTemplate(){
        return new RestTemplate();
    }
}
