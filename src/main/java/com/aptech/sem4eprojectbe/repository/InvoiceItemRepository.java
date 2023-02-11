package com.aptech.sem4eprojectbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aptech.sem4eprojectbe.entity.InvoiceItemEntity;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, String> {
    public List<InvoiceItemEntity> findByDeletedIsFalse();
}
