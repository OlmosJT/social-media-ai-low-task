package com.example.socialmedialowai.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter @ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1024)
    private String body;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @ManyToMany(mappedBy = "likedPosts")
    private Set<User> likedBy;
}
