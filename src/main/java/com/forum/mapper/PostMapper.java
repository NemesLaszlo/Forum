package com.forum.mapper;

import com.forum.dto.PostRequest;
import com.forum.dto.PostResponse;
import com.forum.model.Post;
import com.forum.model.Subforum;
import com.forum.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subforum", source = "subforum")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subforum subforum, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subforumName", source = "subforum.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
