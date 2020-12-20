package com.mainserver.oskelly.service.impl;

import com.mainserver.oskelly.dto.CommentDto;
import com.mainserver.oskelly.entity.CommentEntity;
import com.mainserver.oskelly.repository.CommentRepository;
import com.mainserver.oskelly.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CommentDto createComment(CommentDto comment) {

        try {
            repository.save(commentDtoToEntity(comment));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }


    private CommentEntity commentDtoToEntity(CommentDto dto) {
        CommentEntity entity = new CommentEntity();

        entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        entity.setTime(dto.getTime());

        return entity;
    }
}
