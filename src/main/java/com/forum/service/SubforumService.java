package com.forum.service;

import com.forum.dto.SubforumDto;
import com.forum.exceptions.SpringForumException;
import com.forum.mapper.SubforumMapper;
import com.forum.model.Subforum;
import com.forum.repository.SubforumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubforumService {

    private final SubforumRepository subforumRepository;
    private final SubforumMapper subforumMapper;

    @Transactional
    public SubforumDto create(SubforumDto subforumDto) {
        Subforum subforum = subforumRepository.save(subforumMapper.mapDtoToSubforum(subforumDto));
        subforumDto.setId(subforum.getId());
        return subforumDto;
    }



    @Transactional(readOnly=true)
    public List<SubforumDto> getAll() {
         return subforumRepository.findAll()
                 .stream()
                 .map(subforumMapper::mapSubforumToDto)
                 .collect(toList());
    }

    public SubforumDto getSubforumById(Long id) {
        Subforum subforum = subforumRepository.findById(id).orElseThrow(() -> new SpringForumException("No subreddit found with ID - " + id));
        return subforumMapper.mapSubforumToDto(subforum);
    }

    /*
    private Subforum mapSubforumDto(SubforumDto subforumDto) {
        return Subforum.builder().name(subforumDto.getName()).description(subforumDto.getDescription()).build();
    }

    private SubforumDto mapToDto(Subforum subforum) {
        return SubforumDto.builder().name(subforum.getName()).id(subforum.getId()).numberOfPosts(subforum.getPosts().size()).build();
    }
    */


}
