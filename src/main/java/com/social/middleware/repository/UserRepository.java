package com.social.middleware.repository;

import com.social.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mithundas on 11/30/14.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
