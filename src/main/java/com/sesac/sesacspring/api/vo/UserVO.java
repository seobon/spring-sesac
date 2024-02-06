package com.sesac.sesacspring.api.vo;

import lombok.Getter;

@Getter
public class UserVO {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
