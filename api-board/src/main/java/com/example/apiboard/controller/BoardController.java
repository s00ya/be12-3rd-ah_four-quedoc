package com.example.apiboard.controller;

import com.example.apiboard.model.Board;
import com.example.apiboard.model.BoardDto;
import com.example.apiboard.service.BoardService;
import com.example.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name="Board API", description = "Board API 입니다.")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    @Operation(summary = "게시글 등록", description = "게시글 정보를 받아 게시글을 등록하는 API입니다.")
    public BaseResponse<String> createBoard(@RequestBody BoardDto.requestDto requestDto) {

        boardService.save(Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(requestDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build());

        return BaseResponse.success("ok");
    }

    @GetMapping("/getAll")
    @Operation(summary = "모든 게시글 불러오기", description = "모든 게시글의 정보를 불러오는 API입니다.")
    public BaseResponse<List<BoardDto.responseDto>> getAllBoards() {
        List<Board> boardList = boardService.findAll();

        List<BoardDto.responseDto> responseDtoList = boardList.stream()
                .map(BoardDto.responseDto::new)
                .collect(Collectors.toList());

        return BaseResponse.success(responseDtoList);
    }

    @GetMapping("/{boardIdx}")
    @Operation(summary = "게시글 상세 조회", description = "게시글의 상세 정보를 조회하기 위한 API입니다.")
    public BaseResponse<BoardDto.responseDto> getBoardById(@RequestParam Long boardIdx) {
        Board board = boardService.findById(boardIdx);
        BoardDto.responseDto responseDto = new BoardDto.responseDto(board);
        return BaseResponse.success(responseDto);
    }

    @GetMapping("/findTitle")
    @Operation(summary = "제목으로 게시글 조회", description = "요청한 단어를 포함한 제목을 가진 게시글을 조회하는 API입니다.")
    public BaseResponse<List<BoardDto.responseDto>> findBoardsByTitle(@RequestParam String title) {
        List<Board> boardList = boardService.findByTitleContaining(title);
        List<BoardDto.responseDto> responseDtoList = boardList.stream()
                .map(BoardDto.responseDto::new)
                .collect(Collectors.toList());

        return BaseResponse.success(responseDtoList);
    }

    @GetMapping("/delete")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제하는 API입니다.")
    public BaseResponse<String> deleteBoard(@RequestParam Long boardIdx) {
        return BaseResponse.success("ok");
    }

}
