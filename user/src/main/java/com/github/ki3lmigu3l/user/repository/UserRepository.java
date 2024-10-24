package com.github.ki3lmigu3l.user.repository;

import com.github.ki3lmigu3l.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
