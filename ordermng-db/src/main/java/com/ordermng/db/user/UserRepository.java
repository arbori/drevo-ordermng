package com.ordermng.db.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.name = :name and u.active = true")
    public Optional<UserEntity> findActiveByName(@Param("name") String name);

    @Query("select u from UserEntity u where u.active = true")
    public Iterable<UserEntity> findAllActive();

    @Query("select 1 from UserEntity u where u.name = :name and u.active = true")
    public Optional<Integer> checkUserExists(@Param("name") String name);
}
