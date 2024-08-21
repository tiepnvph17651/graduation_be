package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Color;
import com.example.demo.repository.ColorRepository;
import com.example.demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;
    @Override
    public Color saveColor(Color color) throws BusinessException {
        return null;
    }

    @Override
    public Color deleteColor(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public Color getColor(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
}
