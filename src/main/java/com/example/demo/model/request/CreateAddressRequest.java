package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {

    private int id;

    private Long userID;

    @NotBlank(message = "is.not.blank")
    private String fullName;

    @NotBlank(message = "is.not.blank")
    private String address;

    @NotBlank(message = "is.not.blank")
    @Pattern(regexp = "^(0[1-9]|84[1-9])([0-9]{8,9})$", message = "is.not.valid.")
    private String numberPhone;

    @NotNull(message = "is.not.null")
    private Integer streetCode;

    @NotBlank(message = "is.not.blank")
    private String streetName;

    @NotNull(message = "is.not.null")
    private Integer wardCode;

    @NotBlank(message = "is.not.blank")
    private String wardName;

    @NotNull(message = "is.not.null")
    private Integer cityCode;

    @NotBlank(message = "is.not.blank")
    private String cityName;

    private String fulladdress;

    private String addressType;

    private Boolean isDefault = false;

    public String getFulladdress() {
        return this.address + "," + this.cityName + "," + this.wardName + "," + this.streetName;
    }

}
