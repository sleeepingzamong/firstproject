package com.example.firstproject.api;

import com.example.firstproject.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class commentsApiController {
    private CommentService commentService;
}
