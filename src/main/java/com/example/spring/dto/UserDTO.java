package com.example.spring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class UserDTO {
    private Long id;
    private String name;
}
