package com.example.demo.service;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Sole;

import java.util.List;

public interface SoleService {
    //    BrandsResponse getBrands(BrandsRequest request, int page, int size) throws BusinessException;
    Sole saveSole(Sole Sole) throws BusinessException;

    Sole deleteSole(Integer id) throws BusinessException;

    Sole getSole(Integer id) throws BusinessException;

    List<Sole> getAllSoles();
}
