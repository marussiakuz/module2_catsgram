package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.FriendsPostsRequest;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.FriendsPostsService;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostFeedController {
    private final FriendsPostsService friendsPostsService;

    @Autowired
    public PostFeedController(FriendsPostsService friendsPostsService) {
        this.friendsPostsService = friendsPostsService;
    }

    @PostMapping(value = "/feed/friends")
    public List<Post> getFriendsPosts(@RequestBody FriendsPostsRequest friendsPostsRequest) {
        System.out.println("friendsPostsRequest "+ friendsPostsRequest);
        return friendsPostsService.getFriendsPosts(friendsPostsRequest);
    }
}
