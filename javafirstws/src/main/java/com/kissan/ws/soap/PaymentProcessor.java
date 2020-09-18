package com.kissan.ws.soap;

import com.kissan.ws.soap.dto.PaymentProcessorRequest;
import com.kissan.ws.soap.dto.PaymentProcessorResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PaymentProcessor {

	@WebMethod
	public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest);
}
