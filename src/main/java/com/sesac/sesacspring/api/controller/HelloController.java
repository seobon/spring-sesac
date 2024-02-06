package com.sesac.sesacspring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
// @Controller : 해당 클래스가 Controller 의 역할을 하는 클래스 는 것을
// Spring Container 에게 알려준다
public class HelloController {
    @GetMapping("/hi")
    // URl 을 매핑시켜주는 친구
    // 클라이언트가 /hi 라는 경로로 GET method 로
    // 접근한다면 아래 메소드를 실행시켜라
    public String getHi(Model model){
        // Model : Controller 안에 메서드가 파라미터로 받을 수 있는 객체 중 하나
        // Model 안에 정보를 담아서 view 로 전달
        // IoC : 개발자가 직접 model 을 생성하지 않음.

        model.addAttribute("name", "손주연");
        model.addAttribute("name2", "<strong>이루다</strong>");
        model.addAttribute("age", 27);
//        ArrayList<String> items = new ArrayList<>();

        String[] x = {"a", "b", "c", "d", "e"};
        model.addAttribute("item1", x);
        char[] alphabetArray = new char[26];
        char alphabet = 'A';

        for (int i = 0; i < 26; i++) {
            alphabetArray[i] = alphabet;
            alphabet++;
        }
        model.addAttribute("item2", alphabetArray);
        return "hi"; // 템플릿 파일의 이름
        // res.render("hi")
        // res.render("hi", { name: '손주연' }) -> X
    }

    @GetMapping("/people")
    public String getPerson(Model model){
        ArrayList<String> nameArr = new ArrayList<>();
        nameArr.add("Eunseo");
        nameArr.add("Luda");
        nameArr.add("Seola");
        nameArr.add("Exy");

        ArrayList<Integer> ageArr = new ArrayList<>();
        ageArr.add(27);
        ageArr.add(28);
        ageArr.add(31);
        ageArr.add(30);

        ArrayList<Person> personArrayList = new ArrayList<>();
        for (int i = 0; i < nameArr.size(); i++) {
            Person person = new Person(nameArr.get(i), ageArr.get(i));
            personArrayList.add(person);
        }

        model.addAttribute("personArr", personArrayList);

        Person p = new Person("Yeorum", 26);

        return "people";
    }
}
