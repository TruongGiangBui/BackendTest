package com.myapi.DTO;

import lombok.Data;

@Data
public class RegisterFormDTO {
    private String email;
    private String fullname;
    private String password;
}
