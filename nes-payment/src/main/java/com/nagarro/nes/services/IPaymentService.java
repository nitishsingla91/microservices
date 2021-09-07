package com.nagarro.nes.services;

import com.nagarro.nes.dtos.PaymentRequestDO;
import com.nagarro.nes.exceptions.PaymentFailedException;
import com.nagarro.nes.exceptions.PaymentNotFoundException;
import com.nagarro.nes.models.Payment;

public interface IPaymentService {

	Payment makePayment(PaymentRequestDO payment) throws PaymentFailedException;

	Payment getPayment(String orderID) throws PaymentNotFoundException;

}
