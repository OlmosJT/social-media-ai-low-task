package com.example.socialmedialowai.mapper;

import com.example.socialmedialowai.dto.response.PostDTO;
import com.example.socialmedialowai.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {
     public static PostDTO map(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                UserMapper.map(post.getLikedBy().stream().toList())
        );
    }

    public static List<PostDTO> map(List<Post> posts) {
         return posts.stream().map(PostMapper::map).collect(Collectors.toList());
    }
}
