package com.example.spring.entity;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@ToString
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;

}
