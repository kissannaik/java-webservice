package com.kissan.ws.soaptest.ws;

import com.kissan.*;
import com.kissan.wsdlfirst.ws.CustomerOrderServiceImplService;
import org.apache.cxf.feature.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@WebService
//@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class OrderWSClient {

    private static Environment environment;

    public OrderWSClient(Environment environment){
        this.environment = environment;
    }

    @WebMethod
    public String order() throws MalformedURLException {

        CustomerOrderServiceImplService customerOrdersService = new CustomerOrderServiceImplService(new URL(environment.getProperty("order.service.url")));
        //CustomerOrderServiceImplService customerOrdersService = new CustomerOrderServiceImplService(new URL("http://localhost:8080/wsdlfirst/customerordersservice?wsdl"));
        CustomerOrdersPortType customerOrdersPort = customerOrdersService.getCustomerOrderServiceImplPort();

        GetOrdersRequest request = new GetOrdersRequest();
        request.setCustomerId(BigInteger.ONE);

        GetOrdersResponse orders = customerOrdersPort.getOrders(request);

        List<Order> order = orders.getOrder();
        return order.toString();
    }

    @WebMethod
    public boolean createOrder(CreateOrdersRequest request) throws MalformedURLException {
        boolean status = false;

        CustomerOrderServiceImplService customerOrdersService = new CustomerOrderServiceImplService(new URL(environment.getProperty("order.service.url")));
        //CustomerOrderServiceImplService customerOrdersService = new CustomerOrderServiceImplService(new URL("http://localhost:8080/wsdlfirst/customerordersservice?wsdl"));
        CustomerOrdersPortType customerOrdersPort = customerOrdersService.getCustomerOrderServiceImplPort();

        CreateOrdersResponse ordersResponse = customerOrdersPort.createOrders(request);
        status = ordersResponse.isResult();
        return status;
    }

    public static void main(String[] args) throws MalformedURLException {
        OrderWSClient orderWSClient = new OrderWSClient(environment);
        System.out.println(orderWSClient.order());
    }


}
