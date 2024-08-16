package com.example.demo.service;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Size;

import java.util.List;

public interface SizeService {
    //    BrandsResponse getBrands(BrandsRequest request, int page, int size) throws BusinessException;
    Size saveSize(Size size) throws BusinessException;

    Size deleteSize(Integer id) throws BusinessException;

    Size getSize(Integer id) throws BusinessException;

    List<Size> getAllSizes();
}
