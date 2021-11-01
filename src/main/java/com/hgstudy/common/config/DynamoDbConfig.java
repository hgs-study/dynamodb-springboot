package com.hgstudy.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "")
public class DynamoDbConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDbEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDbRegion;

    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;

    @Primary
    @Bean
    public DynamoDBMapper dynamoDbMapper(AmazonDynamoDB amazonDynamoDb) {
        return new DynamoDBMapper(amazonDynamoDb, DynamoDBMapperConfig.DEFAULT);
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDb, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDb, config);
    }

    @Bean(name = "amazonDynamoDB")
    public AmazonDynamoDB amazonDynamoDb() {
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey));
        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(amazonDynamoDbEndpoint, amazonDynamoDbRegion);

        return AmazonDynamoDBClientBuilder.standard()
                                          .withCredentials(credentialsProvider)
                                          .withEndpointConfiguration(endpointConfiguration).build();
    }
}
