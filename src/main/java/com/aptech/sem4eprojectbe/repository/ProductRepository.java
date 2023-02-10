package com.aptech.sem4eprojectbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptech.sem4eprojectbe.entity.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

}
