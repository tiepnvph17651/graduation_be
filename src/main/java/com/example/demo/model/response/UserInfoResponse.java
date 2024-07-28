package com.example.demo.model.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String username;
    private String email;
    private String numberPhone;
    private String address;
    private String avatar;
    private String fullName;
}
