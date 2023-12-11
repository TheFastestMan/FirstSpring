package com.example.spring.dto;

import com.example.spring.entity.Company;
import com.example.spring.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Setter
@Getter
@Component
public class UserDTO {
    private Long id;
    private Role role;
    private String name;
    private List<Company> owner;
}
