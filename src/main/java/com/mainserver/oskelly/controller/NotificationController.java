package com.mainserver.oskelly.controller;

import com.mainserver.oskelly.dto.NotificationDto;
import com.mainserver.oskelly.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService service;

    @PostMapping("/create")
    public NotificationDto createComment(@RequestBody NotificationDto notification) {
        return service.createNotification(notification);
    }

    @GetMapping("/getNotifications")
    public List<NotificationDto> getComments (@RequestParam("page")int page) {
        return service.getNotifications(page);
    }
}
