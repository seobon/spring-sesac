package com.sesac.sesacspring.jpa.repository;

import com.sesac.sesacspring.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository<T, id>
// T : 대상으로 지정한 엔티티, id : 해당 엔티티의 pk 타입
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 1. jpa 의 기본 규칙을 따르는 방법
    // findBy컬럼명
    // select * from student where 컬럼명=''
//    Student findByName(String name); // pk or unique key

//    Student findByNickname(String nikcname);
    // 컬럼명과 변수명은 일치하는 것이 좋다.
    
    // 만약 결과가 여러 개라면 오류가 나니 주의할 것.
    List<Student> findByName(String name);
    // pk or unique key 가 아닌 경우 오류가 나지 않게 list 에 담는다.

    // 이름과 닉네임이 모두 일치하는 경우
    List<Student> findByNameAndNickname(String name, String nickname);
    // 이름이 일치하거나 닉네임이 일치하는 경우
    List<Student> findByNameOrNickname(String name, String nickname);
    
//    findByAgeGreaterThanEqual(int age); age 보다 크거나 같은 값 검색

    // 실습 1
    String countByNickname(String nickname);


    // 2. 직접 쿼리를 작성해서 연결
//    @Query("select s from Student s where s.name = :a")
    @Query(nativeQuery = true, value= "select s from Student s where s.name = :a")
    List<Student> findTest (String a);

    // 실습 2
//    @Query(nativeQuery = true, value= "select s from Student s where s.name = :a")
//    List<Student> findTest(String a);
}
