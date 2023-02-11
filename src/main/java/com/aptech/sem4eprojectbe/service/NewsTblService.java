package com.aptech.sem4eprojectbe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.NewsTblEntity;
import com.aptech.sem4eprojectbe.repository.NewsTblRepository;

@Service
public class NewsTblService {
    @Autowired
    private NewsTblRepository newsTblRepository;

    public NewsTblEntity insertNew(NewsTblEntity news) {
        news.setCreateat(LocalDateTime.now());
        return newsTblRepository.save(news);
    }

    public List<NewsTblEntity> getAllNews() {
        return newsTblRepository.findByDeletedIsFalse();
    }

    public Optional<NewsTblEntity> getNewById(IdModel idModel) {
        return newsTblRepository.findById(idModel.getId());
    }

    public NewsTblEntity updateNew(NewsTblEntity news) {
        return newsTblRepository.findById(news.getId())
                .map(newItem -> {
                    newItem.setImage(news.getImage());
                    newItem.setContent(news.getContent());
                    newItem.setTitle(news.getTitle());
                    newItem.setDeleted(news.getDeleted());
                    return newsTblRepository.save(newItem);
                })
                .orElseGet(() -> {
                    NewsTblEntity newNews = new NewsTblEntity();
                    newNews.setCreateat(news.getCreateat());
                    newNews.setImage(news.getImage());
                    newNews.setContent(news.getContent());
                    newNews.setTitle(news.getTitle());
                    newNews.setDeleted(false);
                    return newsTblRepository.save(newNews);
                });
    }

}
