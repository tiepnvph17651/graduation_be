package com.example.demo.service;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Style;

import java.util.List;

public interface StyleService {
    //    BrandsResponse getBrands(BrandsRequest request, int page, int size) throws BusinessException;
    Style saveStyle(Style Style) throws BusinessException;

    Style deleteStyle(Integer id) throws BusinessException;

    Style getStyle(Integer id) throws BusinessException;

    List<Style> getAllStyles();
}

