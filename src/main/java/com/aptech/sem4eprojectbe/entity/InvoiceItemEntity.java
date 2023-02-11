package com.aptech.sem4eprojectbe.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="invoiceitem")
public class InvoiceItemEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    
    private String invoiceid;
    private String productid;
    private int quantity;
    private BigDecimal totalprice;
    private Boolean deleted;
}
