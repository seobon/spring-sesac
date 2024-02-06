package com.sesac.sesacspring.mybatis.controller;

import com.sesac.sesacspring.mybatis.dto.UserDTO;
import com.sesac.sesacspring.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Mybatis 수업 controller
//@RestController
@RequestMapping("/user") // 아래에 적힐 모든 url 앞에 /user를 붙여주는 역할
public class UserController {
    // C, R
    // 1. Table 생성 완료 ( user )
    // 2. domain 만들기 ( domain/user )
    // 3. mapper 만들기
    // 4. service 만들기
    // 5. controller 만들기
    // 지금 거꾸로 만드는 중입니다 실제로 배포할땐 거꾸로 해야해요.
    @Autowired
    UserService userService;
    @GetMapping("/all") // /user/all
    public List<UserDTO> getUser(){
        List<UserDTO> result = userService.retreiveAll();
        return result;
    }

    @GetMapping("/user") // /user/user
    public String getUserInsert(
            @RequestParam String name,
            @RequestParam String nickname
    ){
        userService.createUser(name, nickname);
        return "Success";
    }

    @GetMapping("/userUpdate")
    public String UserUpdate(
            @RequestParam int id,
            @RequestParam String nickname
    ){
        userService.UpdateUser(id, nickname);
        return "Update Success";
    }

    @GetMapping("/userDelete")
    public String UserDelete(
            @RequestParam int id
    ){
        userService.DeleteUser(id);
        return "Delete Success";
    }
}
