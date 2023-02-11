package com.aptech.sem4eprojectbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.FeedbackEntity;
import com.aptech.sem4eprojectbe.repository.FeedbackRepository;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackEntity insertFeedback(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findByDeletedIsFalse();
    }

    public Optional<FeedbackEntity> getFeedbackById(IdModel idModel) {
        return feedbackRepository.findById(idModel.getId());
    }

    public FeedbackEntity updateFeedback(FeedbackEntity feedback) {
        return feedbackRepository.findById(feedback.getId())
                .map(feedbackItem -> {
                    feedbackItem.setUserId(feedback.getUserId());
                    feedbackItem.setContent(feedback.getContent());
                    feedbackItem.setProductId(feedback.getProductId());
                    feedbackItem.setFirstName(feedback.getFirstName());
                    feedbackItem.setLastName(feedback.getLastName());
                    feedbackItem.setDeleted(feedback.getDeleted());
                    return feedbackRepository.save(feedbackItem);
                })
                .orElseGet(() -> {
                    FeedbackEntity newFeedback = new FeedbackEntity();
                    newFeedback.setUserId(feedback.getUserId());
                    newFeedback.setContent(feedback.getContent());
                    newFeedback.setCreateat(feedback.getCreateat());
                    newFeedback.setProductId(feedback.getProductId());
                    newFeedback.setFirstName(feedback.getFirstName());
                    newFeedback.setLastName(feedback.getLastName());
                    newFeedback.setDeleted(false);
                    return feedbackRepository.save(newFeedback);
                });
    }

}
