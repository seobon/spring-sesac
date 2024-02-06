package com.sesac.sesacspring.mybatis.controller;

import com.sesac.sesacspring.mybatis.dto.BoardDTO;
import com.sesac.sesacspring.mybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    // 5 개의 method
    // 1. 전체 조회 -> board.html 렌더링
    // 2. 작성 ( create ) : axios ( 동적폼전송, post ) == @RequestBody
    // 3. 수정 ( update ) : axios ( 동적폼전송, patch )
    // 2. 삭제 ( delete ) : axios ( 동적폼전송, delete )
    // 2. 검색(조회) : axios ( 동적폼전송,  get )
    
    // 서비스 파일과 마찬가지로 여기도 Autowired 사용
    @Autowired 
    BoardService boardService;

    // 1. 전체 조회
    @GetMapping("")
    public String getBoard(Model model){
        List<BoardDTO> result = boardService.boardAll();
        model.addAttribute("result", result);
        return "board";
    }

    @PostMapping("")
    @ResponseBody // 응답을 주기 위함
    public Boolean insertBoard(
            // 동적 폼 전송이기 때문에 @RequestBody 를 붙여주어야 함
            @RequestBody BoardDTO boardDTO
    ){
        // 2. 게시글 작성
        boardService.insertBoard(boardDTO);
        return true;
    }

    @PatchMapping("")
    @ResponseBody
    // @ResponseBody 를 사용하지 않으면, Void 라서 아무것도 return 하지 않기 때문에
    // 현재 템플릿을 그대로 다시 보여준다.
    public void patchBoard(
            @RequestBody BoardDTO boardDTO
    ){
        boardService.patchBoard(boardDTO);
    }

    @DeleteMapping("")
    public String deleteBoard(
            @RequestParam int id
    ){
        boardService.deleteBoard(id);
        return "board";
    }

    @GetMapping("/search")
    @ResponseBody
    public int searchBoard(@RequestParam String word){
        List<BoardDTO> result = boardService.boardAll();
        return boardService.searchBoard(word);
    }
}
