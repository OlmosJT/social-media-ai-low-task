package com.example.socialmedialowai.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Getter @Setter @ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1024)
    private String body;
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(nullable = false)
    private UserEntity author;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @ManyToMany(mappedBy = "likedPosts")
    private Set<UserEntity> likedBy;

    public Post() {

    }


}
