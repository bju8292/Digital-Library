package com.gfg.JBDL_76_MinorP1.repository;

import com.gfg.JBDL_76_MinorP1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 1 way by which you can write the method name and it is going to return you the response
    // no query  at all , we are  going to use the most

    List<User> findByName(String  name);
    List<User> findByNameLike(String name);

    // 2 way of writing the query
    @Query(value = "select u from User u where  name=:name", nativeQuery = false)
    List<User> retrieveUserName(String name);

    // 3 ways
    @Query(value = "select * from user where name=:name", nativeQuery = true)
    List<User> retrieveUserViaNameNative(String name);

    User findByEmail(String email);
}
