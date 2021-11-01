package com.hgstudy.business.post.api;

import com.hgstudy.business.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/create")
    public void createTable(){
        postService.createTable();
    }

    @GetMapping("/delete")
    public void deleteTable(){
        postService.deleteTable();
    }

    @GetMapping("/insert")
    public void insertItem(){
        postService.insertTable();
    }

}
