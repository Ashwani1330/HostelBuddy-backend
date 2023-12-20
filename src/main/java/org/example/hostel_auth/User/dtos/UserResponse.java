package org.example.hostel_auth.User.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String image;
}
