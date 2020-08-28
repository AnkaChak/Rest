package com.example.demo.configuration.DynamoDb;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.persistence.DynamoDB.repositories.ProductInfoRepository;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = ProductInfoRepository.class)
public class DynamoDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String dynamoDbEndpoint;

	@Value("${amazon.aws.accessKey}")
	private String awsAccessKey;

	@Value("${amazon.aws.secretKey}")
	private String awsSecretKey;

	@Value("${amazon.aws.signingRegion}")
	private String signingRegion;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().
				withCredentials(amazonAWSCredentials())
				.withRegion(signingRegion)
				.build();

		return amazonDynamoDB;
	}

	@Bean
	public AWSCredentialsProvider amazonAWSCredentials() {
		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
	}

	@Bean
	public EndpointConfiguration dynamoDBEndPointConfiguration() {
		return new EndpointConfiguration(dynamoDbEndpoint, signingRegion);
	}
	
	/*
	 * @Bean public DynamoDBMapper dynamoDBMapper() { return new
	 * DynamoDBMapper(amazonDynamoDB()); }
	 */
}