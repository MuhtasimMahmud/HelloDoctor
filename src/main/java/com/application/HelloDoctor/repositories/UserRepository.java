package com.application.HelloDoctor.repositories;

import com.application.HelloDoctor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("select u from User u where u.email = :email")
    public User findByEmail(@Param("email") String email);

}
