package com.member.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String membershipType;
    private Long phonenumber;
    private String birthdate;
}
