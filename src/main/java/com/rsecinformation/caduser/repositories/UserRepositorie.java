package com.rsecinformation.caduser.repositories;

import com.rsecinformation.caduser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorie extends JpaRepository<User, Long> {

}
