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
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    
    private String firstname;
    private String lastname;
    private String address;
    private String district;
    private String city;
    private String role;
    private String phone;
    private String email;
    private String password;
    private Boolean deleted;
}
