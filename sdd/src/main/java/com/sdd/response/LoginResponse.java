package com.sdd.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String useName;
    private String token;
    private Integer userLevel;
}
