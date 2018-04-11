package com.customer.app.dao;

import java.math.BigDecimal;

public class Payment {
	
//	private BigDecimal payment;
//
//	public BigDecimal getPayment() {
//		return payment;
//	}
//
//	public void setPayment(BigDecimal payment) {
//		this.payment = payment;
//	}
	
	private BigDecimal paymentForProduct;
    private int invoiceId;

    public BigDecimal getPaymentForProduct() {
        return paymentForProduct;
    }

    public void setPaymentForProduct(BigDecimal paymentForProduct) {
        this.paymentForProduct = paymentForProduct;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }


	
	

}

