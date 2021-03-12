package com.irpan.projek.laundry.repository;

import com.irpan.projek.laundry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT m FROM User m WHERE m.id_active = true")
    List<User> findAll();

    @Query(value = "SELECT m FROM User m WHERE m.id_active = true AND m.id=:id ")
    User find(@Param("id") Integer id);

    @Query(value = "UPDATE user m SET m.id_active = false WHERE m.id=:id ")
    User delete(@Param("id") Integer id);
}
