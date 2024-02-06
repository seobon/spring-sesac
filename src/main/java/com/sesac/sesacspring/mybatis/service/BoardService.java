package com.sesac.sesacspring.mybatis.service;

import com.sesac.sesacspring.mybatis.domain.Board;
import com.sesac.sesacspring.mybatis.dto.BoardDTO;
import com.sesac.sesacspring.mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    // Service 파일을 만들었으면 가장 먼저 Autowired 어노테이션을 이용해서
    // 의존성을 주입 받은 mapper 를 불러와야 됨.
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDTO> boardAll() {
        List<Board> boards = boardMapper.boardAll();
        List<BoardDTO> result = new ArrayList<>();

        for ( Board board : boards ) {
            BoardDTO boardDTO = new BoardDTO();

            // 값을 넣어주는 방법은 생성자를 주입하는 방법과
            // setter 를 이용해 값을 넣어주는 방법이 있었다.
            // 나는 setter 를 사용했다.
            boardDTO.setId(board.getId());
            boardDTO.setTitle(board.getTitle());
            boardDTO.setContent(board.getContent());
            boardDTO.setWriter(board.getWriter());
            boardDTO.setCreatedAt(board.getCreatedAt());
            boardDTO.setNo(100 + board.getId());

            result.add(boardDTO);
        }
        return result;
    }

    public boolean insertBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());

        boardMapper.insertBoard(board);
        return true;
    }

    public void patchBoard(BoardDTO boardDTO) {
        // board.getId // title, content, writer
        Board board = new Board();
        board.setId(boardDTO.getId()); // update 의 where
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());

        boardMapper.patchBoard(board);
    }
//    public void updateBoard(int id, String title, String content, String writer) {
//        boardMapper.updateBoard(id, title, content, writer);
//    }
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    public int searchBoard(String word) {
        List<Board> result = boardMapper.searchBoard(word);
        return result.size();
    }
}
