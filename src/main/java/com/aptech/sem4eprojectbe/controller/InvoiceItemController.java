package com.aptech.sem4eprojectbe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.common.model.ResponseModel;
import com.aptech.sem4eprojectbe.entity.InvoiceItemEntity;
import com.aptech.sem4eprojectbe.service.InvoiceItemService;


@RestController
@RequestMapping("/api/v1")
public class InvoiceItemController {
    
    @Autowired
    private InvoiceItemService invoiceItemService;

    @PostMapping("/insert-invoiceItem")
    public ResponseModel insertInvoiceItem(@RequestBody InvoiceItemEntity invoiceItem){
        return new ResponseModel("ok", "success", invoiceItemService.insertInvoiceItem(invoiceItem));
    }

    @GetMapping("/invoice-items")
    public ResponseModel getAllInvoiceItem(){
        return new ResponseModel("ok", "success", invoiceItemService.getAllInvoiceItem());
    }

    @GetMapping("/invoice-item")
    public ResponseModel getInvoiceItemById(@RequestBody IdModel idModel){
        Optional<InvoiceItemEntity> invoiceItem = invoiceItemService.getInvoiceItemById(idModel);
        if (invoiceItem.isPresent() && !invoiceItem.get().getDeleted()) {
            return new ResponseModel("ok", "success", invoiceItem.get());
        } else {
            return new ResponseModel("fail", "Cannot find faq id: " + idModel.getId(), null);
        }
    }

    @PutMapping("/update-invoice-item")
    public ResponseModel updateInvoiceItem(@RequestBody InvoiceItemEntity invoiceItem){
        return new ResponseModel("ok", null, invoiceItemService.updateInvoiceItem(invoiceItem));
    }

    @DeleteMapping("/delete-invoice-item")
    public ResponseModel deleteInvoiceItem(@RequestBody IdModel idModel){
        Optional<InvoiceItemEntity> invoiceItem = invoiceItemService.getInvoiceItemById(idModel);
        if(invoiceItem.isPresent()) {
            InvoiceItemEntity invoiceItemDelete = invoiceItem.get();
            invoiceItemDelete.setDeleted(true);
            invoiceItemService.updateInvoiceItem(invoiceItemDelete);
            return new ResponseModel("ok", "success", null);

        } else {
            return new ResponseModel("fail", "Can not find id:" + idModel.getId(), null);
        }
    }
}
