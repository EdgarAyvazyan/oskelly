package com.mainserver.oskelly.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String comment;
    private LocalDateTime time;

}
