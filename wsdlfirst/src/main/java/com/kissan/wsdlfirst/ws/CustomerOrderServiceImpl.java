package com.kissan.wsdlfirst.ws;

import com.kissan.*;
import org.apache.cxf.feature.Features;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CustomerOrderServiceImpl implements CustomerOrdersPortType {

    private Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    private int customerNum;

    public CustomerOrderServiceImpl(){
        init();
    }

    private void init() {
        Product product = new Product();
        product.setId("1");
        product.setDescription("IPhone");
        product.setQuantity(BigInteger.ONE);

        Order order = new Order();
        order.setId(BigInteger.ONE);
        order.getProduct().add(product);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        addOrder(orders);
    }

    private void addOrder(List<Order> orders) {
        customerOrders.put(BigInteger.valueOf(++customerNum), orders);
    }


    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();

        List<Order> orders = customerOrders.get(customerId);

        GetOrdersResponse response = new GetOrdersResponse();
        response.getOrder().addAll(orders);
        return response;
    }

    @Override
    public CreateOrdersResponse createOrders(CreateOrdersRequest request) {

        BigInteger customerId = request.getCustomerId();
        Order order = request.getOrder();

        List<Order> orders = customerOrders.get(customerId);
        orders.add(order);

        CreateOrdersResponse response = new CreateOrdersResponse();
        response.setResult(true);
        return response;
    }

    @Override
    public DeleteOrdersResponse deleteOrders(DeleteOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        BigInteger orderId = request.getOrderId();

        List<Order> orders = customerOrders.get(customerId);
        for(Order order:orders){
            if(order.getId().equals(orderId)) {
                orders.remove(order);
                break;
            }
        }

        DeleteOrdersResponse response = new DeleteOrdersResponse();
        response.setResult(true);
        return response;
    }
}
