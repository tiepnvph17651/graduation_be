package com.example.demo.model.response;

import lombok.Data;

@Data
public class AddressResponseSave {
    private int id;
    private int userID;
    private String numberPhone;
    private String address;
    private String fulladdress;
    private String fullName;
    private Boolean isDefault;
    private String addressType;
    private Integer streetCode;
    private String streetName;
    private Integer wardCode;
    private String wardName;
    private Integer cityCode;
    private String cityName;
}
