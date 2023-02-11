package com.aptech.sem4eprojectbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.InvoiceItemEntity;
import com.aptech.sem4eprojectbe.repository.InvoiceItemRepository;


@Service
public class InvoiceItemService {
    
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    public InvoiceItemEntity insertInvoiceItem(InvoiceItemEntity invoiceItem){
        return invoiceItemRepository.save(invoiceItem);
    }

    public List<InvoiceItemEntity> getAllInvoiceItem(){
        return invoiceItemRepository.findByDeletedIsFalse();
    }

    public Optional<InvoiceItemEntity> getInvoiceItemById(IdModel idModel){
        return invoiceItemRepository.findById(idModel.getId());
    }

    public InvoiceItemEntity updateInvoiceItem(InvoiceItemEntity invoiceItem){
        return invoiceItemRepository.findById(invoiceItem.getId())
        .map(newInvoiceItem -> {
            newInvoiceItem.setQuantity(invoiceItem.getQuantity());
            newInvoiceItem.setTotalprice(invoiceItem.getTotalprice());
            newInvoiceItem.setDeleted(invoiceItem.getDeleted());
            return invoiceItemRepository.save(newInvoiceItem);
        })
        .orElseGet(() -> {
            return null;
        });
    }
}
