package com.sesac.sesacspring.api.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Getter
@Setter
public class Person {
    private String name;
    private int age;
    // lombok
    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public static void main(String[] args) {

    }
}
