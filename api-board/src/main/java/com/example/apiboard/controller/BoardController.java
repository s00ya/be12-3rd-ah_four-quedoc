package com.example.apiboard.controller;

import com.example.apiboard.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name="Board API", description = "Board API 입니다.")
public class BoardController {
    private final BoardService boardService;


}
