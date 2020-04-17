package com.project.Toshop.service;

import com.project.Toshop.entity.PaymentCallback;
import com.project.Toshop.entity.PaymentDetail;

public interface PaymentService {

    PaymentDetail proceedPayment(PaymentDetail paymentDetail);
    String payuCallback(PaymentCallback paymentResponse);

}
