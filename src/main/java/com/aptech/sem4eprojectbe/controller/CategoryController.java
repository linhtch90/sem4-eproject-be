package com.aptech.sem4eprojectbe.controller;

import java.util.Optional;

import org.hibernate.ReplicationMode;
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
import com.aptech.sem4eprojectbe.entity.CategoryEntity;
import com.aptech.sem4eprojectbe.service.CategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert-category")
    public ResponseModel insertCategory(@RequestBody CategoryEntity category){
          return new ResponseModel("ok", "success", categoryService.insertCategory(category));

    }

    @GetMapping("/getAllCategory")
    public ResponseModel getAllCategory(){
        return new ResponseModel("ok ", "success", categoryService.getAllCategory());
    }

    @GetMapping("/getCategoryById")
    public ResponseModel getCategoryById(@RequestBody IdModel idModel){
        Optional<CategoryEntity> cate = categoryService.getCategoryById(idModel);
        if(cate.isPresent() && !cate.get().getDeleted()){
            return new ResponseModel("ok", "success", cate.get());
        } else {
            return new ResponseModel("fail", "Can not find id " + idModel.getId(), null);
        }
    }

    @PutMapping("/updateCategory")
    public ResponseModel updateCategory(@RequestBody CategoryEntity category){
        return new ResponseModel("ok", "success", categoryService.updateCategory(category));
    }    

    @DeleteMapping("/deleteCategory")
    public ResponseModel deleteCategory(@RequestBody IdModel idModel){
        Optional<CategoryEntity> cate = categoryService.getCategoryById(idModel);
        if(cate.isPresent()){
            Optional<CategoryEntity> cateEntity = categoryService.getCategoryById(idModel);
            CategoryEntity deleteCate = cateEntity.get();
            categoryService.updateCategory(deleteCate);
            return new ResponseModel("ok", "success", null);
        } else {
            return new ResponseModel("fail", "Can not find id : " + idModel.getId(), null);
        }
    }
}
