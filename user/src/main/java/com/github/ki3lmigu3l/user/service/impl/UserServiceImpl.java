package com.github.ki3lmigu3l.user.service.impl;

import com.github.ki3lmigu3l.user.model.User;
import com.github.ki3lmigu3l.user.producer.UserProducer;
import com.github.ki3lmigu3l.user.repository.UserRepository;
import com.github.ki3lmigu3l.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserServiceImpl(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Override
    @Transactional
    public User save(User user) {
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
