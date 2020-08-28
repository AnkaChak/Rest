package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.demo.Domain.DynamoDB.ProductInfo;
import com.example.demo.persistence.DynamoDB.repositories.ProductInfoRepository;

@Service
public class DynamoDBService {

	private ProductInfoRepository productInfoRepository;
	private DynamoDBMapper dynamoDBMapper;
	private AmazonDynamoDB amazonDynamoDb;

	public DynamoDBService(ProductInfoRepository productInfoRepository, DynamoDBMapper dynamoDBMapper,
			AmazonDynamoDB amazonDynamoDb) {
		super();
		this.productInfoRepository = productInfoRepository;
		this.dynamoDBMapper = dynamoDBMapper;
		this.amazonDynamoDb = amazonDynamoDb;
	}

	public void createRecord(ProductInfo pi) {

		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);

		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

		TableUtils.createTableIfNotExists(amazonDynamoDb, tableRequest);

		productInfoRepository.save(pi);

	}
	
	public ProductInfo findById (String id) {
		return productInfoRepository.findById(id).orElseThrow();
	}

}
