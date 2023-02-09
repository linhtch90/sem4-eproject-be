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
import com.aptech.sem4eprojectbe.entity.FaqEntity;
import com.aptech.sem4eprojectbe.service.FaqService;

@RestController
@RequestMapping("/api/v1")
public class FaqController {
    @Autowired
    private FaqService faqService;

    @PostMapping("/insert-faq")
    public ResponseModel insertFaq(@RequestBody FaqEntity faq) {
        return new ResponseModel("ok", "success", faqService.insertFaq(faq));
    }

    @GetMapping("/faqs")
    public ResponseModel getAllFaqs() {
        return new ResponseModel("ok", "success", faqService.getAllFaqs());
    }

    @GetMapping("/faq")
    public ResponseModel getFaqById(@RequestBody IdModel idModel) {
        Optional<FaqEntity> faq = faqService.getFaqById(idModel);
        if (faq.isPresent()) {
            return new ResponseModel("ok", "success", faq.get());
        } else {
            return new ResponseModel("fail", "Cannot find faq id: " + idModel.getId(), null);
        }
    }

    @PutMapping("/update-faq")
    public ResponseModel updateFaq(@RequestBody FaqEntity faq) {
        return new ResponseModel("ok", "success", faqService.updateFaq(faq));
    }

    @DeleteMapping("/delete-faq")
    public ResponseModel removeFaqById(@RequestBody IdModel idModel) {
        Optional<FaqEntity> faq = faqService.getFaqById(idModel);
        if (faq.isPresent()) {
            FaqEntity deletedFaq = faq.get();
            deletedFaq.setDeleted(true);
            faqService.updateFaq(deletedFaq);
            return new ResponseModel("ok", "success", null);
        } else {
            return new ResponseModel("fail", "Cannot find faq id: " + idModel.getId(), null);
        }
    }
}
