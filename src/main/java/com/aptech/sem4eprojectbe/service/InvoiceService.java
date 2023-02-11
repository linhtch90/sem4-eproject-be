package com.aptech.sem4eprojectbe.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.InvoiceEntity;
import com.aptech.sem4eprojectbe.repository.InvoiceRepository;

@Service
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceEntity insertInvoice(InvoiceEntity invoice){
        invoice.setCreateat(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }

    public List<InvoiceEntity> getAllInvoice(){
        return invoiceRepository.findByDeletedIsFalse();
    }

    public Optional<InvoiceEntity> getInvoiceById(IdModel idModel){
        return invoiceRepository.findById(idModel.getId());
    }

    public InvoiceEntity deleteInvoice(InvoiceEntity invoice){
        return invoiceRepository.findById(invoice.getId())
        .map(invoiceItem -> {
            invoiceItem.setDeleted(invoice.getDeleted());
            return invoiceRepository.save(invoiceItem);
        })
        .orElseGet(() -> {
            return null;
        });
    }
}
