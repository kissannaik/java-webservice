package com.kissan.ws.soap.config;

import com.kissan.ws.soap.PaymentProcessorImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.WSS4JConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private Environment environment;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
        endpoint.publish("/paymentProcessor");

        // Configure Security
        Map<String, Object> securityMap = new HashMap<>();
        securityMap.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
        securityMap.put(ConfigurationConstants.PASSWORD_TYPE, WSConstants.PASSWORD_TEXT);
        securityMap.put(ConfigurationConstants.PW_CALLBACK_CLASS, UTPasswordCallback.class.getName());

        WSS4JInInterceptor inInterceptor = new WSS4JInInterceptor(securityMap);
        endpoint.getInInterceptors().add(inInterceptor);

        return endpoint;
    }
}

