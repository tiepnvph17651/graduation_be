package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL_DETAIL")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_BILL",referencedColumnName = "ID")
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUCT_DETAIL",referencedColumnName = "ID")
    private ProductDetail productDetail;
}
