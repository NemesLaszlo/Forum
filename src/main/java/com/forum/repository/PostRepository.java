package com.forum.repository;

import com.forum.model.Subforum;
import com.forum.model.Post;
import com.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Post Long->Primary Key
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubforum(Subforum subforum);
    List<Post> findByUser(User user);
}
