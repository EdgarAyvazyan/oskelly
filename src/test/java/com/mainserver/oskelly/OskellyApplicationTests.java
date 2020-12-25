package com.mainserver.oskelly;

import com.mainserver.oskelly.dto.CommentsDto;
import com.mainserver.oskelly.repository.CommentRepository;
import com.mainserver.oskelly.service.CommentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class OskellyApplicationTests {

    @Autowired
    private CommentService commentService;

    @Test
    void testAddCommentSuccess() {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setTime(LocalDateTime.now());

        for (int i = 0; i < 10; i++) {
            commentsDto.setComment("Comment " + i);

            final CommentsDto saved = commentService.createComment(commentsDto);
            Assert.assertNotNull(saved.getId());
        }
    }

    @Test
    void testAddCommentFail() {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setTime(LocalDateTime.now());

        for (int i = 0; i < 1000; i++) {
            commentsDto.setComment("Comment " + i);

            final CommentsDto saved = commentService.createComment(commentsDto);
            Assert.assertNull(saved.getId());
        }
    }

}
