package com.example.demo.service;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Material;

import java.util.List;

public interface MaterialService {

    Material saveMaterial(Material material) throws BusinessException;

    Material deleteMaterial(Integer id) throws BusinessException;

    Material getMaterial(Integer id) throws BusinessException;

    List<Material> getAllMaterials();
}
