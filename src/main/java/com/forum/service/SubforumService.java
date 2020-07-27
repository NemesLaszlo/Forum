package com.forum.service;

import com.forum.dto.SubforumDto;
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

    @Transactional
    public SubforumDto create(SubforumDto subforumDto) {
        Subforum subforum = subforumRepository.save(mapSubforumDto(subforumDto));
        subforumDto.setId(subforum.getId());
        return subforumDto;
    }

    private Subforum mapSubforumDto(SubforumDto subforumDto) {
        return Subforum.builder().name(subforumDto.getName()).description(subforumDto.getDescription()).build();
    }

    @Transactional(readOnly=true)
    public List<SubforumDto> getAll() {
         return subforumRepository.findAll()
                 .stream()
                 .map(this::mapToDto)
                 .collect(toList());
    }

    private SubforumDto mapToDto(Subforum subforum) {
        return SubforumDto.builder().name(subforum.getName()).id(subforum.getId()).numberOfPosts(subforum.getPosts().size()).build();
    }


}
