package com.hgstudy.business.post.repository;

import com.hgstudy.business.post.entity.Post;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface PostRepository extends CrudRepository<Post,String> {

}
