package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SHIPPING_MONEY")
    private BigDecimal shippingMoney;

    @Column(name = "CODE")
    private String code;

    @Column(name = "STATUS")
    private String status;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "SHIPPING_DATE")
    private Date shippingDate;

    @Column(name = "DATE_OF_PAYMENT")
    private Date dateOfPayment;

    @Column(name = "RECIPIENT_NAME")
    private String recipientName;

    @Column(name = "RECIPIENT_PHONE_NUMBER")
    private String recipientPhoneNumber;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CUSTOMER",referencedColumnName = "ID")
    private User user;
}
