package com.github.ki3lmigu3l.user.service.impl;

import com.github.ki3lmigu3l.user.model.User;
import com.github.ki3lmigu3l.user.repository.UserRepository;
import com.github.ki3lmigu3l.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
