package com.aptech.sem4eprojectbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptech.sem4eprojectbe.entity.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{
    public List<ProductEntity> findByDeletedIsFalse();
 
}
