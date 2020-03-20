package com.project.Toshop.vo.response;

import lombok.Data;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String account;
    private String name;
    private String role;
    private Long id;

    public JwtResponse(String token, String account, String name, String role, Long id) {
        this.account = account;
        this.name = name;
        this.token = token;
        this.role = role;
        this.id= id;
    }
}
