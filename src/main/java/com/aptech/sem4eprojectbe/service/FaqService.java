package com.aptech.sem4eprojectbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.FaqEntity;
import com.aptech.sem4eprojectbe.repository.FaqRepository;

@Service
public class FaqService {
    @Autowired
    private FaqRepository faqRepository;

    public FaqEntity insertFaq(FaqEntity faq) {
        return faqRepository.save(faq);
    }

    public List<FaqEntity> getAllFaqs() {
        return faqRepository.findByDeletedIsFalse();
    }

    public Optional<FaqEntity> getFaqById(IdModel idModel) {
        return faqRepository.findById(idModel.getId());
    }

    public FaqEntity updateFaq(FaqEntity faq) {
        return faqRepository.findById(faq.getId())
                .map(faqItem -> {
                    faqItem.setQuestion(faq.getQuestion());
                    faqItem.setAnswer(faq.getAnswer());
                    faqItem.setDeleted(faq.getDeleted());
                    return faqRepository.save(faqItem);
                })
                .orElseGet(() -> {
                    FaqEntity newFaq = new FaqEntity();
                    newFaq.setQuestion(faq.getQuestion());
                    newFaq.setAnswer(faq.getAnswer());
                    newFaq.setDeleted(false);
                    return faqRepository.save(newFaq);
                });
    }

}
