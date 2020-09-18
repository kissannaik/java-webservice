package com.kissan.ws.client;

import com.kissan.ws.soap.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class JavafirstWSClient {

    public static void main(String[] args) throws MalformedURLException, DatatypeConfigurationException {

        PaymentProcessorImplService paymentProcessorImplService = new PaymentProcessorImplService(new URL("http://localhost:8080/paymentProcessor?wsdl"));
        PaymentProcessor paymentProcessorImplPort = paymentProcessorImplService.getPaymentProcessorImplPort();
        // Configure Security
        Client client = ClientProxy.getClient(paymentProcessorImplPort);
        Endpoint endpoint = client.getEndpoint();

        Map<String, Object> props = new HashMap<>();
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        props.put(WSHandlerConstants.USER, "tom");
        props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, UTPasswordCallback.class.getName());
        WSS4JOutInterceptor wss4JOutInterceptor = new WSS4JOutInterceptor(props);
        endpoint.getOutInterceptors().add(wss4JOutInterceptor);

        // Create Request Object
        PaymentProcessorRequest request = new PaymentProcessorRequest();
        request.setAmount(1000.0);
        CreditCardInfo creditCardInfo = new CreditCardInfo();
        creditCardInfo.setCardNumber("1111");
        creditCardInfo.setAddress("Bangalore");
        creditCardInfo.setFirstName("Kissan");
        creditCardInfo.setLastName("Naik");
        creditCardInfo.setSecCode("123");
        //creditCardInfo.setExpirtyDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-06-28+06:00"));
        request.setCreditCardInfo(creditCardInfo);
        // Call Service
        PaymentProcessorResponse paymentProcessorResponse = paymentProcessorImplPort.processPayment(request);
        System.out.println("result = " + paymentProcessorResponse.isResult());


    }
}
