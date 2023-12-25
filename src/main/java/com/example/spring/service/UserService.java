package com.example.spring.service;


import com.example.spring.dto.CompanyDTO;
import com.example.spring.dto.UserDTO;
import com.example.spring.entity.Company;
import com.example.spring.listener.AccessType;
import com.example.spring.listener.EventEntity;
import com.example.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Transactional
    public Long registerUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.CREATE, "Before"));
        userRepository.save(user);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.CREATE, "After"));
        return user.getId();
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> findUserById(Long id) {
        User user = userRepository.findUserById(id);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.CREATE, "Before"));
        UserDTO userDTO = userMapper.userToUserDTO(user);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.CREATE, "Before"));
        return Optional.of(userDTO);
    }

    public String updateUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.UPDATE, "Before"));
        boolean userVar = userRepository.update(user);
        applicationEventPublisher.publishEvent(new EventEntity(user,
                AccessType.UPDATE, "After"));
        if (userVar == true) {
            return "User has updated";
        }
        return "User has NOT updated!";
    }

    public String deleteUser(Long id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            applicationEventPublisher.publishEvent(new EventEntity(user,
                    AccessType.UPDATE, "Before"));
            boolean userVar = userRepository.delete(id);
            applicationEventPublisher.publishEvent(new EventEntity(user, AccessType.DELETE, "After"));
            return userVar ? "User has deleted" : "User has NOT deleted!";
        }
        return "User has NOT deleted!";
    }
}
