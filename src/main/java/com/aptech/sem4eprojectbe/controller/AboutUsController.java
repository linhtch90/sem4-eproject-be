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
import com.aptech.sem4eprojectbe.entity.AboutUsEntity;
import com.aptech.sem4eprojectbe.service.AboutUsService;

@RestController
@RequestMapping("/api/v1")
public class AboutUsController {
    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping("/insert-aboutus")
    public ResponseModel insertAboutUs(@RequestBody AboutUsEntity aboutUs){
        return new ResponseModel("ok", "success", aboutUsService.insertAboutUs(aboutUs));
    }

    @GetMapping("/getAllAboutUs")
    public ResponseModel getAllAboutUs(){
        return new ResponseModel("ok", "success", aboutUsService.getAllAboutUs());
    }

    @GetMapping("/getAboutUsById")
    public ResponseModel getAboutUsById(@RequestBody IdModel idModel){
        Optional<AboutUsEntity> aboutUs = aboutUsService.getAboutUsById(idModel);
        if(aboutUs.isPresent()){
            return new ResponseModel("ok", "success", aboutUs.get());
        } else {
            return new ResponseModel("fail", "Can not find id " + idModel.getId(), null);
        }
    }

    @PutMapping("/update-aboutUs")
    public ResponseModel updateAboutUs(@RequestBody AboutUsEntity aboutUs){
        return new ResponseModel("ok", "success", aboutUsService.updateAboutUs(aboutUs));
    }

    @DeleteMapping("/deleteAboutById")
    public ResponseModel deleteAboutUsById(@RequestBody IdModel idModel){
        Optional<AboutUsEntity> aboutUs = aboutUsService.getAboutUsById(idModel);
        if(aboutUs.isPresent()){
            Optional<AboutUsEntity> aboutUsEntity = aboutUsService.getAboutUsById(idModel);
            AboutUsEntity deleteAboutUs = aboutUsEntity.get();
            aboutUsService.updateAboutUs(deleteAboutUs);
            return new ResponseModel("ok", "success", null);
        } else {
            return new ResponseModel("fail", "Can not find " + idModel.getId(), null);
        }

    }
}