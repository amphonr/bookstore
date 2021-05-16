package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.entities.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
    @Query("select u from UserLogin u where u.username = :username")
    Optional<UserLogin> findByUserName(@Param("username") String name);
}
