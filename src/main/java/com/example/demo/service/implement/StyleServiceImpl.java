package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Style;
import com.example.demo.repository.StyleRepository;
import com.example.demo.service.StyleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {
    @Autowired
    StyleRepository styleRepository;
    @Override
    public Style saveStyle(Style Style) throws BusinessException {
        return null;
    }

    @Override
    public Style deleteStyle(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public Style getStyle(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }
}
