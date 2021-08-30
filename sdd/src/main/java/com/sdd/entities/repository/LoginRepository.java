package com.sdd.entities.repository;

import com.sdd.entities.LoginDetails;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails, Integer> {
}
