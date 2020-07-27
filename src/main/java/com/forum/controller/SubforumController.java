package com.forum.controller;

import com.forum.dto.SubforumDto;
import com.forum.service.SubforumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subforum")
@AllArgsConstructor
@Slf4j
public class SubforumController {

    private final SubforumService subforumService;

    @PostMapping
    public ResponseEntity<SubforumDto> createSubforum(@RequestBody SubforumDto subforumDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subforumService.create(subforumDto));
    }

    @GetMapping
    public ResponseEntity<List<SubforumDto>> getAllSubforums() {
        return ResponseEntity.status(HttpStatus.OK).body(subforumService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubforumDto> getSubforum(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subforumService.getSubforumById(id));
    }
}
