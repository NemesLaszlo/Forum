package com.forum.service;

import com.forum.dto.PostRequest;
import com.forum.dto.PostResponse;
import com.forum.exceptions.PostNotFoundException;
import com.forum.exceptions.SubforumNotFoundException;
import com.forum.mapper.PostMapper;
import com.forum.model.Post;
import com.forum.model.Subforum;
import com.forum.model.User;
import com.forum.repository.PostRepository;
import com.forum.repository.SubforumRepository;
import com.forum.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubforumRepository subforumRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void create(PostRequest postRequest) {
        Subforum subforum = subforumRepository.findByName(postRequest.getSubforumName()).orElseThrow(() -> new SubforumNotFoundException(postRequest.getSubforumName()));
        User currentUser = authService.getCurrentUser();

        postRepository.save(postMapper.map(postRequest, subforum, currentUser));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());

    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));

        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubforum(Long subforumId) {
        Subforum subforum = subforumRepository.findById(subforumId).orElseThrow(() -> new SubforumNotFoundException(subforumId.toString()));
        List<Post> posts = postRepository.findAllBySubforum(subforum);

        return posts
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
