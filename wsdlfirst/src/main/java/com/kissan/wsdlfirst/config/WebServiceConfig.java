package com.kissan.wsdlfirst.config;

import com.kissan.wsdlfirst.ws.CustomerOrderServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint getEndpoint(){
        Endpoint endpoint = new EndpointImpl(bus, new CustomerOrderServiceImpl());
        endpoint.publish("/customerordersservice");
        return endpoint;
    }
}
