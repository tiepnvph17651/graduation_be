package com.example.demo.service.implement;

import com.example.demo.model.DTO.ProductSalesDTO;
import com.example.demo.model.DTO.RevenueDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductSalesDTO> getTop10Products() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Object[]> results = productRepository.findTop10Product(pageable);

        List<ProductSalesDTO> topProducts = new ArrayList<>();
        for (Object[] result : results) {
            Integer productId = (Integer) result[0]; // productId
            String productName = (String) result[1]; // productName
            Long salesCount = ((Number) result[2]).longValue(); // totalQuantity
            // Kiểm tra kiểu dữ liệu và chuyển đổi nếu cần
            BigDecimal totalRevenue;
            Object revenueObj = result[3]; // totalRevenue
            if (revenueObj instanceof BigDecimal) {
                totalRevenue = (BigDecimal) revenueObj;
            } else if (revenueObj instanceof Double) {
                totalRevenue = BigDecimal.valueOf((Double) revenueObj);
            } else {
                throw new ClassCastException("Unsupported type for totalRevenue");
            }

            topProducts.add(new ProductSalesDTO(productId, productName, salesCount, totalRevenue));
        }
        return topProducts;
    }
    @Override
    public List<RevenueDTO> getRevenueByDayMonthYear() {
        List<Object[]> results = productRepository.findRevenueByDayMonthYear();
        List<RevenueDTO> revenueList = new ArrayList<>();

        for (Object[] result : results) {
            Integer year = (Integer) result[0];
            Integer month = (Integer) result[1];
            Integer day = (Integer) result[2];
            BigDecimal totalRevenue = BigDecimal.valueOf((Double) result[3]);;

            revenueList.add(new RevenueDTO(year, month, day, totalRevenue));
        }

        return revenueList;
    }

    @Override
    public List<RevenueDTO> getRevenueByMonthYear() {
        List<Object[]> results = productRepository.findRevenueByMonthYear();
        List<RevenueDTO> revenueList = new ArrayList<>();

        for (Object[] result : results) {
            Integer year = (Integer) result[0];
            Integer month = (Integer) result[1];
            BigDecimal totalRevenue = BigDecimal.valueOf((Double) result[2]);

            revenueList.add(new RevenueDTO(year, month, totalRevenue));
        }

        return revenueList;
    }

    @Override
    public List<RevenueDTO> getRevenueByYear() {
        List<Object[]> results = productRepository.findRevenueByYear();
        List<RevenueDTO> revenueList = new ArrayList<>();

        for (Object[] result : results) {
            Integer year = (Integer) result[0];
            BigDecimal totalRevenue = BigDecimal.valueOf((Double) result[1]);

            revenueList.add(new RevenueDTO(year, totalRevenue));
        }

        return revenueList;
    }


}
