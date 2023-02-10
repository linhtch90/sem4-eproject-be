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
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private String categoryid;
    private int alcohol;
    private Boolean deleted;
}
