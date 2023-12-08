package com.example.spring;

import com.example.spring.config.ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import com.example.spring.entity.Company;
import com.example.spring.entity.User;
import com.example.spring.service.CompanyService;
import com.example.spring.service.UserService;

public class SpringRunner {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {

            ConfigurableEnvironment env = context.getEnvironment();
            env.setActiveProfiles("test");


            context.register(ApplicationConfiguration.class);
            context.refresh();


            UserService userService = context.getBean(UserService.class);
            CompanyService companyService = context.getBean(CompanyService.class);

            User user = new User();
            user.setName("Name1");

            Company company = new Company();
            company.setName("Company1");

            userService.registerUser(user);
            companyService.registerCompany(company);

            System.out.println("User is " + userService.findUserById(1L));
            System.out.println("Company is " + companyService.findCompanyById(1L));
        }
    }
}
