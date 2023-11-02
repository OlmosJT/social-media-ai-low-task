# ReadMe.md
### Building a RESTful API for a simple social media application using Spring Boot, Hibernate, and PostgreSQL

This project is a simple social media application built using Spring Boot, Hibernate, and PostgreSQL. The application allows users to create and view posts, follow other users, and like posts. Each post has a title, body, and author.

#### Prerequisites:
- Java 11 or higher
- Maven 3.6.3 or higher
- PostgreSQL 13 or higher

#### Building and running the application:

##### To build and run the application, run the following commands:
```
mvn clean install
mvn spring-boot:run
```
The application will be running on port `8080` by default. You can access the application in your web browser at `http://localhost:8080/`.

### Using the API:
####The API provides the following endpoints:
**User Enpoints:**
- POST *api/users/register*: This endpoint allows you to create a new user.
- GET *api/users*: This endpoint allows you get all users list.
- GET PUT DELETE *api/users/{userId}*: This endpoint allows you get, update or delete user by ID.
- POST DELETE *api/users/{userId}/follow*: This endpoint allows user (un)follow to another user.
**Post Enpoints:**
- GET POST *api/posts*: This endpoint allows you to view all of the posts in the system or create a new one.
- GET PUT DELETE *api/posts/{postId}*: This endpoint allows you to view or update or delete a specific post by ID.
- POST DELETE *api/posts/{postId}/likes*: This endpoint allows you to (un)like a specific post by ID.

### Database Schema
```
Posts
id SERIAL PRIMARY KEY
title VARCHAR(255) NOT NULL
body TEXT NOT NULL
author_id INT NOT NULL
created_at TIMESTAMP NOT NULL DEFAULT now()
updated_at TIMESTAMP NOT NULL DEFAULT now()

Users
id SERIAL PRIMARY KEY
username VARCHAR(255) NOT NULL UNIQUE
email VARCHAR(255) NOT NULL UNIQUE
password VARCHAR(255) NOT NULL

Followers
follower_id INT NOT NULL
following_id INT NOT NULL
PRIMARY KEY (follower_id, following_id)

Likes
post_id INT NOT NULL
user_id INT NOT NULL
PRIMARY KEY (post_id, user_id)
```
graph LR
    subgraph Posts
        id
        title
        body
        author_id
    end
    subgraph Users
        id
        username
        email
        password
    end
    subgraph Followers
        follower_id
        following_id
    end
    subgraph Likes
        post_id
        user_id
    end
    Posts --> Users [Author]
    Users --> Posts [Created by]
    Users --> Followers [Follows]
    Users --> Followers [Followed by]
    Followers --> Users [Follower]
    Followers --> Users [Following]
    Posts --> Likes [Liked by]
    Users --> Likes [Likes]
    Likes --> Posts [Post]
    Likes --> Users [User]
