package com.sesac.sesacspring.api.controller;

import com.sesac.sesacspring.api.vo.PracticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PracticeController {
    @GetMapping("/")
    public String getMain() { return "practice"; }

    @GetMapping("/introduce/{param1}")
    public String introduce(
            @PathVariable String param1,
            Model model
    ){
        model.addAttribute("name", param1);
        return "practiceResult";
    }

    @GetMapping("/introduce2")
    public String introduce2(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") String age,
            Model model
    ){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "practiceResult";
    }

    @PostMapping("/postPractice")
    public String postPractice(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender", required = false) String gender,
//            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "day", required = false) String day,
            @RequestParam(value = "interest", required = false) String interest,
            Model model
    ){
        String birth = year + "-" + month + "-" + day;
        model.addAttribute("name", name);
        model.addAttribute("gender", gender);
        model.addAttribute("birth", birth);
        model.addAttribute("interest", interest);
        return "practiceResult";
    }

    @PostMapping("/axiosVoPractice")
    @ResponseBody
    public String axiosVoRes5(@RequestBody PracticeVO practiceVO){
        return practiceVO.getName() + "님 회원가입이 완료되았습니다.";
    }
}
