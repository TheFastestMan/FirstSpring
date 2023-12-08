package com.example.spring.mapper;

import com.example.spring.dto.CompanyDTO;
import com.example.spring.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO toCompanyDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        return dto;
    }

    public Company toCompany(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        return company;
    }
}
