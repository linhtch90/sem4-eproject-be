package com.aptech.sem4eprojectbe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column(name = "userid")
    private String userId;

    private String content;
    private LocalDateTime createat;

    @Column(name = "productid")
    private String productId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private Boolean deleted;
}
