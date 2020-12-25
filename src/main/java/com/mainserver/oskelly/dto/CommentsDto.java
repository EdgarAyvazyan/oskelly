package com.mainserver.oskelly.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private String comment;
    private LocalDateTime time;

}
