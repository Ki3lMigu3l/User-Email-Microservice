package com.github.ki3lmigu3l.email.repository;

import com.github.ki3lmigu3l.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
