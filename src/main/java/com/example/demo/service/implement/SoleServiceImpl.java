package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.Sole;
import com.example.demo.repository.SoleRepository;
import com.example.demo.service.SoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SoleServiceImpl implements SoleService {
    @Autowired
    SoleRepository soleRepository;
    @Override
    public Sole saveSole(Sole Sole) throws BusinessException {
        return null;
    }

    @Override
    public Sole deleteSole(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public Sole getSole(Integer id) throws BusinessException {
        return null;
    }

    @Override
    public List<Sole> getAllSoles() {
        return soleRepository.findAll();
    }
}