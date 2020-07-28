package com.forum.mapper;

import com.forum.dto.SubforumDto;
import com.forum.model.Post;
import com.forum.model.Subforum;
import com.forum.model.User;
import com.forum.repository.PostRepository;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SubforumMapper {

    @Autowired
    private PostRepository postRepository;

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subforum))")
    public abstract SubforumDto mapSubforumToDto(Subforum subforum);

    Integer mapPosts(Subforum subforum) {
        return postRepository.findAllBySubforum(subforum).size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    public abstract Subforum mapDtoToSubforum(SubforumDto subforumDto, User user);

}
