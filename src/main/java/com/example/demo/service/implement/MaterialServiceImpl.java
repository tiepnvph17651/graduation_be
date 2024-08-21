package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Material;
import com.example.demo.repository.MaterialRepository;
import com.example.demo.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialRepository materialRepository;
    @Override
    public Material saveMaterial(Material material) throws BusinessException {
        return null;
    }

    @Override
    public Material deleteMaterial(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public Material getMaterial(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
