package com.example.demo.service;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Color;

import java.util.List;

public interface ColorService {
    Color saveColor(Color color) throws BusinessException;

    Color deleteColor(Integer id) throws BusinessException;

    Color getColor(Integer id) throws BusinessException;

    List<Color> getAllColors();
}
