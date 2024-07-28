package com.example.demo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCustomerRequest {
    @NotBlank(message = "is.not.blank")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "is.not.valid")
    private String username;

    @NotBlank(message = "is.not.blank")
    private String fullName;

    @NotBlank(message = "is.not.blank")
    @Pattern(regexp = "^(0[1-9]|84[1-9])([0-9]{8,9})$", message = "is.not.valid.")
    private String numberPhone;

    private String gender;

    private Date birthOfDate;

    @Email(message = "is.not.valid")
    @NotBlank(message = "is.not.blank")
    private String email;

    private String roles;

    @NotBlank(message = "is.not.blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",message = "password.is.not.valid")
    private String password;
    private String status;
}
