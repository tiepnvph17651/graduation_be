package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_history")
public class ShippingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "BILL_ID")
    private Integer billId;
    private String status;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    private String title;
    private String content;
    @Column(name = "PARENT_ID")
    private Integer parentId;
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
