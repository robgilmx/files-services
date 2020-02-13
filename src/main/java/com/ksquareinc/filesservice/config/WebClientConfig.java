package com.ksquareinc.filesservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Primary
    WebClient webClient(ClientRegistrationRepository clientRegistrations, OAuth2AuthorizedClientRepository authorizedClients, LoadBalancerClient loadBalancerClient) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
        oauth.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient))
                .filter(oauth)
                .build();
    }
}
