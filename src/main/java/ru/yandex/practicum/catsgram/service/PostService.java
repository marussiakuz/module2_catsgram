package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidIdException;
import ru.yandex.practicum.catsgram.exceptions.PageDoesNotExistException;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.LocalDate;
import java.util.*;

@Service // использовали аннотацию @Service — она делает то же самое, что @Component.
public class PostService {

    private final Map<Post, String> posts = new HashMap<>();
    private final UserService userService;
    private final static Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(Integer size, String sort, Integer page) {
        List<Post> copyPosts = posts.keySet().stream().sorted((p1, p2) -> {
            int index = p1.getCreationDate().compareTo(p2.getCreationDate());
            if (sort.equals("asc")) index = -1 * index;
            return index;
        }).toList();
        log.debug("Текущее количество постов: {}", copyPosts.size());
        if (page == 1) return size > copyPosts.size()? copyPosts : copyPosts.subList(0, size);
        if (copyPosts.size() <= size*(page-1)) throw new PageDoesNotExistException();
        return copyPosts.size() <= size*page ? copyPosts.subList((page-1)*size, copyPosts.size())
                : copyPosts.subList((page-1)*size, page*size);
    }

    public Post create(Post post) {
        /*if (!userService.checkUserByEmail(post.getAuthor()))
            throw new UserNotFoundException(String.format("Пользователь $s не найден", post.getAuthor()));*/
        posts.put(post, post.getAuthor());
        return post;
    }

    public Optional<Post> findById(Integer postId) {
        checkId(postId);
        return posts.keySet().stream()
                .filter(x -> x.getId().equals(postId))
                .findFirst();
    }

    public List<Post> getPostsByAuthor(String author) {
        return posts.keySet().stream().filter(p -> p.getAuthor().equals(author)).toList();
    }

    private void checkId(Integer id) {
        if (id == null || id < 0) throw new InvalidIdException();
    }
}
