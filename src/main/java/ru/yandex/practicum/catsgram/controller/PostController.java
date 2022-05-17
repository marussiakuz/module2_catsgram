package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final PostService postService;

    // создание PostService и его передача в PostController произойдёт автоматически.
    @Autowired  // PostService должен стать зависимостью для PostController
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(defaultValue = "10", required = false) Integer size,
                              @RequestParam(defaultValue = "asc", required = false) String sort,
                              @RequestParam(defaultValue = "1", required = false) Integer page) {
        System.out.println("Integer size "+ size +" String sort "+ sort +" Integer page "+ page);
        return postService.findAll(size, sort, page);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable Integer postId) {
        return postService.findById(postId);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }
}