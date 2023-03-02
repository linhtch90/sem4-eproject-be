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
import com.aptech.sem4eprojectbe.entity.ProductEntity;
import com.aptech.sem4eprojectbe.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert-product")
    public ResponseModel insertProduct(@RequestBody ProductEntity product) {
        return new ResponseModel("ok", "success", productService.insertProduct(product));
    }

    @GetMapping("/products")
    public ResponseModel getAllProducts() {
        return new ResponseModel("ok", "success", productService.getAllProduct());
    }

    @PostMapping("/product")
    public ResponseModel getProductById(@RequestBody IdModel idModel) {
        Optional<ProductEntity> product = productService.getProductById(idModel);
        if (product.isPresent() && !product.get().getDeleted()) {
            return new ResponseModel("ok", "success", product);
        } else {
            return new ResponseModel("fail", "Can not find id : " + idModel.getId(), null);
        }
    }

    @PutMapping("/update-product")
    public ResponseModel updateProduct(@RequestBody ProductEntity product) {
        return new ResponseModel("ok", "success", productService.updateProductEntity(product));
    }

    @DeleteMapping("/delete-product")
    public ResponseModel deleteProduct(@RequestBody IdModel idModel) {
        Optional<ProductEntity> product = productService.getProductById(idModel);
        if (product.isPresent()) {
            ProductEntity updateProduct = product.get();
            updateProduct.setDeleted(true);
            productService.updateProductEntity(updateProduct);
            return new ResponseModel("ok", "success", null);
        } else {
            return new ResponseModel("fail", "Can not find id :" + idModel.getId(), null);
        }
    }

}
