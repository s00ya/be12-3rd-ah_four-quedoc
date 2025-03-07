package com.example.apiboard.service;

import com.example.apiboard.model.Board;
import com.example.apiboard.repository.BoardRepository;
import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(Board board) {
        try {
            boardRepository.save(board);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BOARD_SAVE_FAIL);
        }
    }
    public List<Board> findAll() {
        try {
            return boardRepository.findAll();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BOARD_LOAD_FAIL);
        }
    }
    public Board findById(Long idx) {
        Optional<Board> board;
        try {
            board = boardRepository.findById(idx);
            if(board.isEmpty()) {
                throw new CustomException(ErrorCode.BOARD_LOAD_FAIL);
            }
            return board.get();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BOARD_LOAD_FAIL);
        }
    }
    public List<Board> findByTitleContaining(String title) {
        try {
            return boardRepository.findByTitleContaining(title);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BOARD_LOAD_FAIL);
        }
    }
}
