package com.example.socialmedialowai.service.impl;

import com.example.socialmedialowai.dto.PostRequest;
import com.example.socialmedialowai.model.Post;
import com.example.socialmedialowai.model.UserEntity;
import com.example.socialmedialowai.repository.PostRepository;
import com.example.socialmedialowai.repository.UserRepository;
import com.example.socialmedialowai.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptySet;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) throws EntityNotFoundException {
        if(!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        return postRepository.findAllByAuthor_Id(userId);
    }

    @Override
    public Post getPostById(Long id) throws EntityNotFoundException {
        return postRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Post not found."));
    }

    @Override
    public Post createPost(PostRequest request) throws EntityNotFoundException {
        UserEntity author = userRepository.findById(request.author_id())
                .orElseThrow(() -> new EntityNotFoundException("Author id not found."));

        Post post = Post.builder()
                .title(request.title())
                .body(request.body())
                .author(author)
                .createdAt(request.createdUpdatedAt())
                .updatedAt(request.createdUpdatedAt())
                .likedBy(emptySet())
                .build();
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(PostRequest request) throws EntityNotFoundException {
        Post post = postRepository.findById(request.id()).orElseThrow(
                () -> new EntityNotFoundException("Post not found.")
        );
        post.setTitle(request.title());
        post.setBody(request.body());
        post.setUpdatedAt(request.createdUpdatedAt());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Post likePost(Long postId, Long userId) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post not found.")
        );
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found.")
        );

        post.getLikedBy().add(user);
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional
    public Post unlikePost(Long postId, Long userId) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post not found.")
        );
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found.")
        );

        post.getLikedBy().remove(user);
        postRepository.save(post);
        return post;
    }
}
