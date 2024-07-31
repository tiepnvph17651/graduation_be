package com.example.demo.model.result;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillResult {
    private int id;
    private String createdBy;
    private String customerName;
    private String numberPhone;
    private BigDecimal total;
    private String status;
    private String createdDate;
    private String note;
    private BigDecimal price;
    private String code;
    private String modifiedBy;
    private String modifiedDate;
    private String receiveDate;
}
