package com.sesac.sesacspring.jpa.service;

import com.sesac.sesacspring.jpa.dto.StudentDTO;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getStudentAll() {
        List<Student> result = studentRepository.findAll();
        List<StudentDTO> students = new ArrayList<>();

        for (Student student : result) {
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

    public String insertStudent(String name, String nickname, Student.LoginType type) {
        // 받아온 데이터로 repository의 save 메소드를 호출
        Student student = Student.builder().name(name).nickname(nickname).type(type).build();

        Student newStudent = studentRepository.save(student);
        // newStudent : save 를 한 후 반환되는 Entity 객체

        return newStudent.getId() + newStudent.getName();
    }

    public String searchStudentByName(String name) {
//        Student student = studentRepository.findByName(name);
//        return "id는 " + student.getId() + "입니다.";
        List<Student> student = studentRepository.findByName(name);
        return "해당하는 이름의 사용자는 " + student.size() + "명 입니다.";
    }

    public String searchStudentById(int id) {
//        Student student = studentRepository.findById(id);
        // 예전에는 나지 않는 에러가 난다. 그 이유는 Optional 이라는 것이 생겼기 때문에 객체로 받을 수 없어서.
        // Optional<T> : java 8 부터 등장한 친구
        // null 일 수도 있는 객체를 감싸는 wrapper 클래스 ( null 일 수도 있는 객체를 받아오기 위해. )

//        if (student != null)
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
        // orElse() : 있으면 반환하고 없으면 다른 값 반환
        // orElseThrow() : 있으면 반환하고 없으면 error 처리
        return student.getName();

//        Optional<Student> student = studentRepository.findById(id);
//
//        if ( student.isPresent() ) {
//            // isPresent : 객체의 여부를 boolean 으로 반환
//            return student.get().getName();
//        }
        // 아 머띿우 머띿우 하는데 어케 적어야 할지 모르겓다아아
    }

    // 실습 1
    public String countByNickname(String nickname) {
        String count = studentRepository.countByNickname(nickname);

//            Student newStudent = studentRepository..count(student);
        return "해당하는 이름의 사용자는 " + count + "명 입니다.";
    }

//    public int countByNickname(String nickname) {
//        String count = studentRepository.countByNickname(nickname);
//        // count 라는 method 를 활용해라
//        // countByNickname(String nickname) = select count(*)
//    }

    public String update(int id, String name) {
        // save(T) : 새로운 entity 를 insert or 기존 entity 를 update
        // T의 기본값(pk)의 상태에 따라 다르게 동작
        // - pk 값이 존제하는 경우 : pk 와 연결된 entity 를 update
        // - pk 값이 없는 경우 : 새로운 entity 를 insert
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id is wrong"));

        Student modifyStudent = Student.builder().id(id).name(name).build();
        studentRepository.save(modifyStudent);
        return "update 완";
    }
}

