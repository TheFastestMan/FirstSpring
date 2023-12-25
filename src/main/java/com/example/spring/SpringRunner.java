package com.example.spring;

import com.example.spring.config.ApplicationConfiguration;
import com.example.spring.dto.CompanyDTO;
import com.example.spring.dto.UserDTO;
import com.example.spring.entity.Role;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import com.example.spring.service.CompanyService;
import com.example.spring.service.UserService;

import java.util.Optional;

public class SpringRunner {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            ConfigurableEnvironment env = context.getEnvironment();
            env.setActiveProfiles("test");

            context.register(ApplicationConfiguration.class);
            context.refresh();

            UserService userService = context.getBean(UserService.class);
            CompanyService companyService = context.getBean(CompanyService.class);

            UserDTO userDTO = new UserDTO();
            userDTO.setRole(Role.ADMIN);
            userDTO.setName("Name12");

            //save user
            Long userId = userService.registerUser(userDTO);


            Optional<UserDTO> savedUserDTO = userService.findUserById(userId);
            if (savedUserDTO.isPresent()) {
                if (savedUserDTO.get().getRole() == Role.ADMIN) {
                    CompanyDTO companyDTO = new CompanyDTO();
                    companyDTO.setName("Company1");
                    companyDTO.setUser(savedUserDTO.get());
                    //save company
                    companyService.registerCompany(companyDTO);
                }
            } else {
                System.out.println("User not found");
            }

        }
    }
}