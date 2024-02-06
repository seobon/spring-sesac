package com.sesac.sesacspring.mybatis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    // DTO 는 컬럼 이름과 필드 이름이 같을 필요가 없음
    private int id;
    private String title, content, writer, createdAt;
    // DB 랑 상관없는 필드도 만들 수 있음
    private int no;
}
