package com.sdd.entities.repository;

import com.sdd.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block,Integer> {

    List<Block> findAllByDistrictCodeAndStateId(Integer districtCode, Integer satetId);

    Block findAllByDistrictCodeAndStateIdAndHealthBlockCode(Integer districtCode, Integer satetId,Integer healthBlockCode);

}
