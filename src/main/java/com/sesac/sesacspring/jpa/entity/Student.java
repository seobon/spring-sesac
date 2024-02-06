package com.sesac.sesacspring.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

// : 데이터베이스의 필드와 변수의 연관관계가 정의된 친구
// : db 테이블에 대응되는 하나의 클래스
@Entity // jpa class Student() {} // 빈 생성자가 필수로 필요하다
@NoArgsConstructor

@Table(name = "Student")
// 원래 자동으로 생성되는 sql 문은 대문자를 전부 소문자로 바꿔버리기 때문에
// 만약 대문자를 사용하고 싶다면 Table 어노테이션을 이용해야한다.
// 근데 버전이 바뀌어서  안된다네? 따로 설정을 해야한단다.

@Getter

// 데이터를 넣으면 에러 생김
// 이유는 Builder 어노테이션 때문이다
@Builder // 필드 전체가 다 들어있는 생성자가 필수로 필요하다.
// Entity 와 Builder 가 필요한 것이 달아 충돌이 일어난다.
// 그래서 Entity 와 Builder 에 필요한 것을 각각 만들어주면 된다

@AllArgsConstructor
// NoArgsConstructor 와 AllArgsConstructor 를 작성함으로써 위 충돌이 해결된다.
public class Student {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;
    // id int primary key auto_increment
    // SEQUENCE : 새로운 오브젝트를 만들어서 ID를 부여하는 방법 ( mysql X )
    // TABLE : SEQUENCE 전략을 흉내낸 전략 -> 모든 DBMS에서 사용 가능
    // ( 원래 없는 것을 억지로 만들어낸 느낌이라 성능이 떨어진다 )

    @Column(name = "name", nullable = false, length = 20)
    private String name;
    // name varchar(20) not null
    @Column(columnDefinition = "TEXT")
    private String nickname;

    // enum
    // 스프링 버전에 따라 될수도 안될수도 있으니 버전을 꼭 확인해라
    // mybatis : 3.0, 3.1
    // jpa enum : 3.1 이상에서 가능
    @Column
    @Enumerated(EnumType.STRING)
    public LoginType type;
    public enum LoginType {
        GOOGLE, KAKAO
    }
    private int age;
}
