package com.customer.app.dao;

import java.math.BigDecimal;

public class Payment {
	
	private double paymentForProduct;
    private int invoiceId;
    
    

    public double getPaymentForProduct() {
        return paymentForProduct;
    }

    public void setPaymentForProduct(double paymentForProduct) {
        this.paymentForProduct = paymentForProduct;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }


	
	

}

