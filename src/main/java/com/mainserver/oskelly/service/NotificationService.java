package com.mainserver.oskelly.service;

import com.mainserver.oskelly.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto createNotification(NotificationDto notification);

    List<NotificationDto> getNotifications(int page);
}
