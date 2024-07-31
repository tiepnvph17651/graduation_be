package com.example.demo.repository;

import com.example.demo.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    Optional<Bill> findByCode(String code);
}