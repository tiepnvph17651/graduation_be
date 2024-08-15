package com.example.demo.repository;

import com.example.demo.entity.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    //    lấy danh sách hóa đơn theo mã hóa đơn và tên khách hàng và số điện thoại và trạng thái
    @Query("SELECT b FROM Bill b WHERE b.code LIKE %:code% AND b.recipientName LIKE %:recipientName% AND b.recipientPhoneNumber LIKE %:recipientPhoneNumber% AND b.status = :status")
    Page<Bill> getAll(@Param("code") String code,
                      @Param("recipientName") String recipientName,
                      @Param("recipientPhoneNumber") String recipientPhoneNumber,
                      @Param("status") String status,
                      Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.code LIKE %:code% AND b.createdBy LIKE %:username% AND b.status = :status")
    Page<Bill> getAll(String code, String username, String status, Pageable pageable);

    @Query(value = "SELECT b.* FROM BILL b " +
            "WHERE " +
            "LOWER(b.CODE) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.RECIPIENT_NAME) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.RECIPIENT_PHONE_NUMBER) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.STATUS) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.CREATED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
            "OR LOWER(FORMAT(b.DATE_OF_PAYMENT, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
            "OR LOWER(FORMAT(b.ESTIMATED_DELIVERY_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
            "OR LOWER(FORMAT(b.MODIFIED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
            "OR LOWER(FORMAT(b.SHIPPING_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            countQuery = "SELECT COUNT(*) FROM BILL b " +
                    "WHERE " +
                    "LOWER(b.CODE) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(b.RECIPIENT_NAME) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(b.RECIPIENT_PHONE_NUMBER) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(b.STATUS) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.CREATED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
                    "OR LOWER(FORMAT(b.DATE_OF_PAYMENT, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
                    "OR LOWER(FORMAT(b.ESTIMATED_DELIVERY_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
                    "OR LOWER(FORMAT(b.MODIFIED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))" + // Giả sử b.someDateField là cột ngày
                    "OR LOWER(FORMAT(b.SHIPPING_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%'))",// Giả sử b.someDateField là cột ngày
            nativeQuery = true)
    Page<Bill> searchBills(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "SELECT b.* FROM BILL b " +
            "WHERE (" +
            "LOWER(b.CODE) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.RECIPIENT_NAME) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.RECIPIENT_PHONE_NUMBER) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.CREATED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.DATE_OF_PAYMENT, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.ESTIMATED_DELIVERY_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.MODIFIED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(FORMAT(b.SHIPPING_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')))" +
            "AND LOWER(b.STATUS) LIKE LOWER(CONCAT('%', :status, '%'))",
            countQuery = "SELECT COUNT(*) FROM BILL b " +
                    "WHERE (" +
                    "LOWER(b.CODE) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(b.RECIPIENT_NAME) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(b.RECIPIENT_PHONE_NUMBER) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.CREATED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.DATE_OF_PAYMENT, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.ESTIMATED_DELIVERY_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.MODIFIED_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(FORMAT(b.SHIPPING_DATE, 'yyyy-MM-dd')) LIKE LOWER(CONCAT('%', :keyword, '%')))" +
                    "AND LOWER(b.STATUS) LIKE LOWER(CONCAT('%', :status, '%'))",
            nativeQuery = true)
    Page<Bill> searchBills(@Param("keyword") String keyword, @Param("status") String status, Pageable pageable);

    Optional<Bill> findByCode(String code);

    }

