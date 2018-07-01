package com.ashleydix.rover.user;

import com.ashleydix.rover.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaseUserRepository<T extends User> extends BaseRepository<T, Long> {
    User findOneByEmail(String email);

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    @Query("select count(a) > 0 from User a where a.email = :email")
    boolean exists(@Param("email") String email);
}
