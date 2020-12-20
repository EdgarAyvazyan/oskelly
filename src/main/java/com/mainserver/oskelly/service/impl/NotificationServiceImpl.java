package com.mainserver.oskelly.service.impl;

import com.mainserver.oskelly.dto.NotificationDto;
import com.mainserver.oskelly.entity.NotificationEntity;
import com.mainserver.oskelly.repository.NotificationRepository;
import com.mainserver.oskelly.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
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
            e.printStackTrace();
        }
        return notification;
    }


    private NotificationEntity notificationDtoToEntity(NotificationDto dto) {
        NotificationEntity entity = new NotificationEntity();

        entity.setId(dto.getId());
        entity.setComment_id(dto.getComment_id());
        entity.setTime(dto.getTime());
        entity.setDelivered(dto.isDelivered());

        return entity;
    }
}
