package com.example.demo.model.response;

import com.example.demo.model.info.PaginationInfo;
import com.example.demo.model.result.BaseResult;
import com.example.demo.model.result.BrandResult;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BrandsResponse{
    private List<BrandResult> brands;
    private PaginationInfo pagination;
}
