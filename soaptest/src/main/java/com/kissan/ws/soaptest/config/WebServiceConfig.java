package com.kissan.ws.soaptest.config;

import com.kissan.ws.soaptest.ws.OrderWSClient;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private Environment environment;

    @Bean
    public Endpoint endpoint(){
        Endpoint endpoint = new EndpointImpl(bus, new OrderWSClient(environment));
        endpoint.publish("/ordersclient");
        return endpoint;
    }
}
