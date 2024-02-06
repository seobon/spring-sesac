package com.sesac.sesacspring.jpa.service;

import com.sesac.sesacspring.jpa.dto.StudentDTO;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getStudentAll(){
        List<Student> result = studentRepository.findAll();
        List<StudentDTO> students = new ArrayList<>();

        for ( Student student : result) {
            // Builder : 객체를 만들 때 순서에 의해 생기는 문제,
            // 명시적이지 못한 문제를 해결하기 위해 나온 방법
            // 생성자 주입 : 여러개의 필드가 있을 때 순서를 지켜줘야 한다.
            // setter : 필드 개수만큼 매번 메소드를 만들어줘야 한
            StudentDTO studentDTO = StudentDTO.builder() // new builder 안에서 nickname 을 호출
                    // builder() 로 만들고 .build(); 로 만든다.
                    // builder 는 필드를 지정하기 때문에 순서가 중요하지 않다.
                    // 필드가 많을 때 유용하다.
                    .name(student.getName())
                    .nickname(student.getNickname())
                    .build();
            // 아래와 같은 코드이다.
//            StudentDTO studentDTO = new StudentDTO();
//            studentDTO.setName("이름") ...
            students.add(studentDTO);
        }
        return students;
    }
}
