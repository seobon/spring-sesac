package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    // sql 문을 작성하거나 xml 파일에서 작업하거나
    
    List<Board> boardAll(); // 전체 조회 ( select * from board; )
    // mapper 와 xml 파일의 메소드는 일치해야한다고 했었다. 그 이유는 아래와 같다.
    // 1) mapper 에는 메소드가 있고, xml 에는 없는 경우 -> 실행했을 때 오류
    // 2) xml 에는 있고, mapper 에는 없는 경우 -> 실행이 안됨

    void insertBoard(Board board); // 삽입 ( insert )
//    @Insert("insert into board(title, content, writer) values(#{title}, #{content}, #{writer})")
//    void createBoard(String title, String content, String writer);

    void patchBoard(Board board); // 수정 ( update )
//    @Update("update board set title = #{title}, content = #{content}, writer = #{writer} where id = #{id}")
//    void updateBoard(int id, String title, String content, String writer);

    void deleteBoard(int id);
//    @Delete("")
//    void deleteBoard(int id);

    List<Board> searchBoard(String word);
}
