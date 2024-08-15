package com.example.demo.service;

import com.example.demo.model.DTO.ProductSalesDTO;
import com.example.demo.model.DTO.RevenueDTO;

import java.util.List;

public interface ProductService {
    List<ProductSalesDTO> getTop10Products();
    List<RevenueDTO> getRevenueByDayMonthYear();
    List<RevenueDTO> getRevenueByMonthYear();
    List<RevenueDTO> getRevenueByYear();
}
