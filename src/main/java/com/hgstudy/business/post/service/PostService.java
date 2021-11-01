package com.hgstudy.business.post.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.hgstudy.business.post.entity.Post;
import com.hgstudy.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDbMapper;


    //테이블 생성
    public void createTable(){
        CreateTableRequest createTableRequest = dynamoDbMapper.generateCreateTableRequest(Post.class)
                .withProvisionedThroughput(new ProvisionedThroughput(2L, 3L));

        System.out.println("createTableRequest = " + createTableRequest);
//        createTableRequest.getGlobalSecondaryIndexes().forEach(
//                idx -> idx
//                        .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
//                        .withProjection(new Projection().withProjectionType("ALL"))
//        );
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }

    //테이블 삭제
    public void deleteTable() {
        DeleteTableRequest deleteTableRequest = dynamoDbMapper.generateDeleteTableRequest(Post.class);
        TableUtils.deleteTableIfExists(amazonDynamoDB, deleteTableRequest);
    }

    //항목 생성
    public void insertTable() {
        Post post = new Post("hyun");

        postRepository.save(post);
    }


    
}
