package com.mainserver.oskelly.controller;

import com.mainserver.oskelly.dto.CommentsDto;
import com.mainserver.oskelly.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService service;

    @PostMapping("/create")
    public CommentsDto createComment(@RequestBody CommentsDto comment) {
        return service.createComment(comment);
    }

    @GetMapping("/getComments")
    public List<CommentsDto> getComments (@RequestParam("page")int page) {
        return service.getComments(page);
    }

}
