package com.myapi.repository;

import com.myapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Override
    List<User> findAll();
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
    User findDistinctFirstByEmail(String email);
    @Modifying
    @Query(value = "insert into user(email,fullname,password) values(:email,:fullname,:password)",nativeQuery = true)
    @Transactional
    void insertNewUser(@Param("email") String email,@Param("fullname") String fullname,@Param("password") String password);
    @Modifying
    @Query(value = "update User u set u.password=:password where u.email=:email")
    @Transactional
    void updatePassword(@Param("email") String email,@Param("password") String password);
}