package com.sdd.entities.repository;

import com.sdd.entities.InfantDiarrheaTest;
import com.sdd.entities.InfantJaundiceTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfantJaundiceTestRepository extends JpaRepository<InfantJaundiceTest,Integer> {
}
