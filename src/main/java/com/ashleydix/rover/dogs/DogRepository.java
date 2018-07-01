package com.ashleydix.rover.dogs;

import com.ashleydix.rover.base.BaseRepository;
import com.ashleydix.rover.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DogRepository extends BaseRepository<Dog, Long> {

    @Query("select count(a) > 0 from Dog a where a.owner = :owner and a.name = :name")
    boolean exists(@Param("owner") User owner, @Param("name") String name);

    Dog findOneByOwnerAndName(User owner, String name);
}
