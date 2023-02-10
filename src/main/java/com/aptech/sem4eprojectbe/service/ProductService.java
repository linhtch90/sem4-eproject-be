package com.aptech.sem4eprojectbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.ProductEntity;
import com.aptech.sem4eprojectbe.repository.ProductRepository;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity insertProduct(ProductEntity product){
        return productRepository.save(product);
    }

    public List<ProductEntity> getAllProduct(){
        return productRepository.findByDeletedIsFalse();
    }
    
    public Optional<ProductEntity> getProductById(IdModel  idModel){
        return productRepository.findById(idModel.getId());
    }

    public ProductEntity updateProductEntity(ProductEntity product){
        return productRepository.findById(product.getId())
        .map(productItem -> {
            productItem.setName(product.getName());
            productItem.setImage(product.getImage());
            productItem.setPrice(product.getPrice());
            productItem.setDescription(product.getDescription());
            productItem.setCategoryid(product.getCategoryid());
            productItem.setAlcohol(product.getAlcohol());
            productItem.setDeleted(product.getDeleted());
            return productRepository.save(productItem);
        })
        .orElseGet(() -> {
            ProductEntity newProduct = new ProductEntity();
            newProduct.setName(product.getName());
            newProduct.setImage(product.getImage());
            newProduct.setPrice(product.getPrice());
            newProduct.setDescription(product.getDescription());
            newProduct.setCategoryid(product.getCategoryid());
            newProduct.setAlcohol(product.getAlcohol());
            newProduct.setDeleted(product.getDeleted());
            return productRepository.save(newProduct);
        });
    }
}
