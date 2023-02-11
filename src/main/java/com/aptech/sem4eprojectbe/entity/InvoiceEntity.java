package com.aptech.sem4eprojectbe.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name="invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    
    private LocalDateTime createat;
    private BigDecimal totalprice;
    private String status;
    private String userid;
    private Boolean deleted;
    
}
