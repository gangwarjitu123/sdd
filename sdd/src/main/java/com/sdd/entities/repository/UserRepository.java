package com.sdd.entities.repository;

import com.sdd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

    List<User> findAllByLevelGreaterThanAndCreatedBy(int level,int userId);

    public User findByMobileNumber(String mobileNumber);


}
