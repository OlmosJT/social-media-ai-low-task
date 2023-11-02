package com.example.socialmedialowai.service.impl;

import com.example.socialmedialowai.dto.request.PostCreationDTO;
import com.example.socialmedialowai.dto.response.PostDTO;
import com.example.socialmedialowai.model.Post;
import com.example.socialmedialowai.model.UserE;
import com.example.socialmedialowai.repository.PostRepository;
import com.example.socialmedialowai.repository.UserRepository;
import com.example.socialmedialowai.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
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
    @Transactional
    public Post createPost(PostCreationDTO dto) throws EntityNotFoundException {
        UserE author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found."));

        Post post = Post.builder()
                .title(dto.title())
                .body(dto.body())
                .author(author)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .likedBy(emptyList())
                .build();

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, PostDTO dto) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post not found.")
        );

        post.setTitle(dto.title());
        post.setBody(dto.body());
        post.setUpdatedAt(dto.createdAt());
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

        UserE user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found.")
        );
        if(!post.getLikedBy().contains(user)) {
            post.like(user);
            postRepository.save(post);
        }
        return post;
    }

    @Override
    @Transactional
    public Post unlikePost(Long postId, Long userId) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post not found.")
        );
        UserE user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found.")
        );

        if(post.getLikedBy().contains(user)) {
            post.unlike(user);
            postRepository.save(post);
        }
        return post;
    }
}
