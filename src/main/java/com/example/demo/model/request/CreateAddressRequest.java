package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("DistrictID")
    private Integer districtID;

    @NotBlank(message = "is.not.blank")
    @JsonProperty("DistrictName")
    private String districtName;

    @NotNull(message = "is.not.null")
    @JsonProperty("WardCode")
    private String wardCode;

    @NotBlank(message = "is.not.blank")
    @JsonProperty("WardName")
    private String wardName;

    @NotNull(message = "is.not.null")
    @JsonProperty("ProvinceID")
    private Integer provinceID;

    @NotBlank(message = "is.not.blank")
    @JsonProperty("ProvinceName")
    private String provinceName;

    private String fullAddress;

    private String addressType;

    private Boolean isDefault = false;

    public String getFulladdress() {
        return this.address + "," + this.provinceName + "," + this.wardName + "," + this.districtName;
    }

}
