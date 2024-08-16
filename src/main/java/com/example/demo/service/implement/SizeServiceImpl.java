package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Size;
import com.example.demo.repository.SizeRepository;
import com.example.demo.service.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;
    @Override
    public Size saveSize(Size size) throws BusinessException {
        return null;
    }

    @Override
    public Size deleteSize(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public Size getSize(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }
}