package com.myapi.repository;

import com.myapi.entity.UserSession;
import com.myapi.entity.UserSessionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserSessionRepository extends JpaRepository<UserSession,String> {
    boolean existsByUserSessionPk(UserSessionPk userSession);

    @Modifying
    @Query(value = "insert into usersession(email,sessionid) values (:email,:sessionid)",nativeQuery = true)
    @Transactional
    void createSession(@Param("email") String email,@Param("sessionid") String sessionid);

    @Modifying
    @Query("delete from UserSession us where us.userSessionPk=:userSessionPk")
    @Transactional
    int deleteSession(UserSessionPk userSessionPk);
}
