package com.example.socialmedialowai.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne(targetEntity = UserE.class)
    @JoinColumn(nullable = false)
    private UserE author;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @ManyToMany()
    @JoinTable(
            name = "Likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserE> likedBy;

    public Post() {

    }

    public void like(UserE user) {
        likedBy.add(user);
    }

    public void unlike(UserE user) {
        likedBy.remove(user);
    }

}
