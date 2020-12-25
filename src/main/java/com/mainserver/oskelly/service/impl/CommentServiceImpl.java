package com.mainserver.oskelly.service.impl;

import com.mainserver.oskelly.BusinessLogic;
import com.mainserver.oskelly.dto.CommentsDto;
import com.mainserver.oskelly.dto.NotificationDto;
import com.mainserver.oskelly.entity.CommentsEntity;
import com.mainserver.oskelly.repository.CommentRepository;
import com.mainserver.oskelly.service.CommentService;
import com.mainserver.oskelly.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final NotificationService notificationService;


    public CommentServiceImpl(CommentRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public CommentsDto createComment(CommentsDto comment) {

        try {
            final CommentsEntity commentsEntity = repository.save(commentDto2Entity(comment));

            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setComment_id(commentsEntity.getId());
            notificationDto.setTime(commentsEntity.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            notificationDto.setDelivered(true);
            notificationService.createNotification(notificationDto);


            try {
                BusinessLogic.doSomeWorkOnCommentCreation();
                comment.setId(commentsEntity.getId());
            } catch (RuntimeException e) {
                repository.delete(commentsEntity);
                String error = "Error during  doSomeWorkOnCommentCreation() method";
                log.error(error + ":" + e.getMessage());
            }



        } catch (Exception e) {
            String error = "cannot create a comment";
            log.error(error + ":" + e.getMessage());
        }

        return comment;
    }

    @Override
    public List<CommentsDto> getComments(int page) {

        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, 10);
        Page<CommentsEntity> resultPage = repository.findAll(pageable);
        if (page > resultPage.getTotalPages()) {
            String error = "cannot get comments";
            log.error(error);
            throw new RuntimeException(error);
        }

        List<CommentsDto> list = new ArrayList<>();

        for (CommentsEntity entity : resultPage) {
            list.add(commentsEntity2Dto(entity));
        }

        return list;
    }


    private CommentsEntity commentDto2Entity(CommentsDto dto) {
        CommentsEntity entity = new CommentsEntity();

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setComment(dto.getComment());
        entity.setTime(Date.from(dto.getTime().atZone(ZoneId.systemDefault()).toInstant()));

        return entity;
    }

    private CommentsDto commentsEntity2Dto(CommentsEntity entity) {
        CommentsDto dto = new CommentsDto();

        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setTime(entity.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return dto;
    }


    @Override
    public CommentsDto findId(long id) {
        final Optional<CommentsEntity> byId = repository.findById(id);
        return commentsEntity2Dto(byId.get());
    }
}
