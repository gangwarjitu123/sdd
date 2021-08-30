package com.sdd.entities.repository;

import com.sdd.entities.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<Village,Integer> {

    List<Village> findByHealthFacilityIdAndHealthSubFacilityId(Integer facilityId,Integer subFacilityId);
}
