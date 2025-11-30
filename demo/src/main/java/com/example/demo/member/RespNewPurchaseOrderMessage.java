package com.example.demo.member;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class RespNewPurchaseOrderMessage {
    private final String orderId;

    private final String orderer;

    private final String paymentId;

    private final int totalPrice;

    private final String status;


    public RespNewPurchaseOrderMessage(String id,String orderer ,String paymentId, int totalPrice, String status) {
        this.orderId = id;
        this.orderer = orderer;
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

}
