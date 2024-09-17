package com.example.demo.repository;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Style;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {
    Boolean existsByNameIgnoreCase(String brandName);
    List<Style> findByStatus(Integer status);

}

