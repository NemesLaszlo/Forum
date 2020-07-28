# Forum

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/834adefe710b439e895a73b838b389ae)](https://www.codacy.com/manual/wow.laszlo/Forum?utm_source=github.com&utm_medium=referral&utm_content=NemesLaszlo/Forum&utm_campaign=Badge_Grade)
[![Maintainability](https://api.codeclimate.com/v1/badges/b199b62b220d9434e88c/maintainability)](https://codeclimate.com/github/NemesLaszlo/Forum/maintainability)

For This Forum using Spring Boot, Spring Security with JPA Authentication, Spring Data JPA with MySQL, Spring MVC.

##### Post time management tool: https://github.com/marlonlom/timeago

##### MapStruct: https://mapstruct.org/

#### Endpoints:

| Entity   | Type | URL                                  | Description                                                    |
| -------- | ---- | ------------------------------------ | -------------------------------------------------------------- |
| User     | POST | api/auth/signup                      | User sing up / registration.                                   |
|          | POST | api/auth/login                       | User logging in, authenticate.                                 |
|          | POST | api/auth/refresh/token               | New authentication token generation for the current user.      |
|          | POST | api/auth/logout                      | Delete actual logged in user token.                            |
|          | GET  | api/auth/accountVerification/{token} | User profile activation.                                       |
| Comment  | POST | api/comments                         | User create a comment to the selected post.                    |
|          | GET  | api/comments/by-post/{postId}        | Read comments of the selected post.                            |
|          | GET  | api/comments/by-user/{userName}      | Read comments of the selected User.                            |
| Post     | POST | api/posts/                           | Create post.                                                   |
|          | GET  | api/posts/{id}                       | Read a single selected post.                                   |
|          | GET  | api/posts/by-subforum/{id}           | Read all posts from the selected forum.                        |
|          | GET  | api/posts/by-user/{username}         | Read all posts of the selected user.                           |
| Subforum | POST | api/subforum                         | Create forum.                                                  |
|          | GET  | api/subforum                         | Read all existing subforum. (Short informations about forums.) |
|          | GET  | api/subforum/{id}                    | Read selected subforum.                                        |
| Vote     | POST | api/votes                            | Vote / Like a post.                                            |
