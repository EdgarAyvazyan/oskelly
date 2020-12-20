package com.mainserver.oskelly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private long id;
    private long comment_id;
    private LocalDateTime time;
    private boolean delivered;
}
