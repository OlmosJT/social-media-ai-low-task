package com.example.socialmedialowai.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Builder
@AllArgsConstructor
@Getter @Setter @ToString
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @ManyToMany(mappedBy = "likedBy")
    private List<Post> likedPosts;
    @ManyToMany
    @JoinTable(
            name = "Followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<UserE> followers;

    public UserE() {

    }
}
