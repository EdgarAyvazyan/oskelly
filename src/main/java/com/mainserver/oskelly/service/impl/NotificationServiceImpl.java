package com.mainserver.oskelly.service.impl;

import com.mainserver.oskelly.dto.CommentsDto;
import com.mainserver.oskelly.dto.NotificationDto;
import com.mainserver.oskelly.entity.CommentsEntity;
import com.mainserver.oskelly.entity.NotificationEntity;
import com.mainserver.oskelly.repository.NotificationRepository;
import com.mainserver.oskelly.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotificationDto createNotification(NotificationDto notification) {
        try {
            repository.save(notificationDtoToEntity(notification));
        }catch (Exception e) {
            String error = "cannot crate a notification";
            log.error(error + ":" + e.getMessage());
            throw new RuntimeException(error + ":" + e.getMessage());
        }
        return notification;
    }

    @Override
    public List<NotificationDto> getNotifications(int page ) {

        org.springframework.data.domain.Pageable pageable = PageRequest.of(page,10);
        Page<NotificationEntity> resultPage = repository.findAll(pageable);
        if (page > resultPage.getTotalPages()) {
            String error = "cannot get notifications";
            log.error(error);
            throw new RuntimeException(error);
        }

        List<NotificationDto> list = new ArrayList<>();

        for (NotificationEntity entity : resultPage) {
            list.add(commentsEntity2Dto(entity));
        }

        return list;
    }

    private NotificationDto commentsEntity2Dto(NotificationEntity entity) {
        NotificationDto dto = new NotificationDto();

        dto.setId(entity.getId());
        dto.setComment_id(entity.getComment_id());
        dto.setTime(entity.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        dto.setDelivered(entity.getDelivered());
        return null;
    }

    private NotificationEntity notificationDtoToEntity(NotificationDto dto) {
        NotificationEntity entity = new NotificationEntity();

        entity.setId(dto.getId());
        entity.setComment_id(dto.getComment_id());
        entity.setTime(Date.from(dto.getTime().atZone(ZoneId.systemDefault()).toInstant()));
        entity.setDelivered(dto.isDelivered());

        return entity;
    }
}
