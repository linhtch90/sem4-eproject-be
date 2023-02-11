package com.aptech.sem4eprojectbe.entity;

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
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    
    private String id;
    private String address;
    private String district;
    private String city;
    private String phone;
    private String email;
    private Boolean deleted;

}
