package com.sesac.sesacspring.jpa.controller;

import com.sesac.sesacspring.jpa.dto.StudentDTO;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//    @GetMapping("/count")
//    public int getCountAll(){
//    }

    // 1. 전체 검색 ( select * from student)
     @GetMapping("/all")
        public List<StudentDTO> getAll(){
            // student의 목록을 전부 가져와서 보여주는 api
            List<StudentDTO> result = studentService.getStudentAll();
            return result;
    //        return studentService.getStudentAll();
        }

    // 2. 삽입 ( insert ~~~ )
    @GetMapping("/insert") // /student/insert?name=이름
    public String insertStudent(
            @RequestParam String name,
            @RequestParam String nickname,
            @RequestParam Student.LoginType type
    ) {
        return studentService.insertStudent(name, nickname, type);
        // 이름, 닉네임, login_type
    }

    // 3. 조건에 따른 검색 ( select * from student where name='' )
    @GetMapping("/search/name") // /student/search/name
    public String searchStudentByName(@RequestParam String name){
         return studentService.searchStudentByName(name);
    }

    // 4. 조건에 따른 검색 (2) ( select * from student where id='' )
    @GetMapping("/search/id") // /student/search/id
    public String searchStudentById(@RequestParam int id){
        return studentService.searchStudentById(id);
    }

    // 실습 1.
//    public int count(String nickname) {
//        return studentRepository.countByNickname(nickname);
//    }

    @GetMapping("/count")
    public String countByNickname(@RequestParam String nickname){
        return studentService.countByNickname(nickname);
    }

    @GetMapping("/update")
    public String update(
            @RequestParam int id,
            @RequestParam String name
            // id : 내가 변경할 데이터의 pk
            // name : 내가 변경할 값
    ){
        return studentService.update(id, name);
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}
}
