package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.FriendsPostsRequest;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsPostsService {
    private List<Post> friendsPosts = new ArrayList<>();
    private PostService postService;
    private UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> getFriendsPosts(FriendsPostsRequest friendsPostsRequest) {
        for (String email : friendsPostsRequest.getFriends()) {
            Optional<User> friend = userService.findByEmail(email);
            if (friend.isPresent()) friendsPosts.addAll(postService.getPostsByAuthor(email));
        }
        return friendsPosts.stream().sorted((p0, p1) -> {
                    int index = p0.getCreationDate().compareTo(p1.getCreationDate());
                    if (friendsPostsRequest.getSort().equals("desc")) index = index * (-1);
                    return index;
                }
        ).toList();
    }
}
