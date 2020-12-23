package com.mainserver.oskelly.service;

import com.mainserver.oskelly.dto.CommentsDto;

import java.util.List;

public interface CommentService {
     CommentsDto createComment(CommentsDto comment);

     List<CommentsDto> getComments(int page);
}
