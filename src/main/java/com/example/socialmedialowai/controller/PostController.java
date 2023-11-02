package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.PostRequest;
import com.example.socialmedialowai.model.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/users/{userId}/posts")
public class PostController {

    @PostMapping
    public Post createPost(@RequestBody PostRequest post) {
        return null;
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
//        postRepository.deleteById(id);
    }

    // Like a post by a user
    @PostMapping("/{postId}/likes")
    public void likePost(@PathVariable Long postId, @RequestBody Long userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        Post post = postRepository.findById(postId).orElse(null);
//
//        if (user != null && post != null) {
//            post.getLikedBy().add(user);
//            postRepository.save(post);
//        }
    }

    // Unlike a post by a user
    @DeleteMapping("/{postId}/likes/{userId}")
    public void unlikePost(@PathVariable Long postId, @PathVariable Long userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        Post post = postRepository.findById(postId).orElse(null);
//
//        if (user != null && post != null) {
//            post.getLikedBy().remove(user);
//            postRepository.save(post);
//        }
    }
}
