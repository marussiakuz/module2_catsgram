package ru.yandex.practicum.catsgram.model;

import java.util.List;

public class FriendsPostsRequest {
    private String sort = "asc";
    private Integer size;
    private List<String> friends;

    public FriendsPostsRequest(String sort, Integer size, List<String> friends) {
        this.sort = sort;
        this.size = size;
        this.friends = friends;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
