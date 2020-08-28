package com.example.demo.persistence.DynamoDB.repositories;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.DynamoDB.ProductInfo;

@EnableScan
@Repository
public interface ProductInfoRepository extends 
  CrudRepository<ProductInfo, String> {
    
    Optional<ProductInfo> findById(String id);
}