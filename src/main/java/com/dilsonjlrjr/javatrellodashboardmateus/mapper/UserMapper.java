package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    void updateSessionUser(@Param("id") Long id, @Param("hashSession") String hashSession, @Param("refreshToken") String refreshToken);

    Optional<User> findByUsername(@Param("username") String username);

    Optional<User> findByRefreshTokenHashSession(@Param("refreshToken") String refreshToken, @Param("hashSession") String hashSession);

    List<User> findAll();

    Optional<User> findById(@Param("id") Long id);
}
