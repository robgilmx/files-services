package com.ksquareinc.filesservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
class ServiceDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    String app;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public Optional<URI> serviceURL(){
        return discoveryClient.getInstances(app)
                .stream()
                .map(ServiceInstance::getUri)
                .findFirst();
    }

    @RequestMapping(value = "actuator/health", method = RequestMethod.GET)
    public ResponseEntity<String> myCustomCheck() {
        String message = "Returning OK Health";
        return ResponseEntity.ok(message);
    }

}
