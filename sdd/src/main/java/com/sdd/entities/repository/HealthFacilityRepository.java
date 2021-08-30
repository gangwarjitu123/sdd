package com.sdd.entities.repository;

import com.sdd.entities.HealthFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthFacilityRepository extends JpaRepository<HealthFacility,Integer> {
    List<HealthFacility> findAllByDistrictCodeAndHealthBlockCodeAndStateId(Integer districtCode,Integer blockCode, Integer stateCode);
    List<HealthFacility> findAllByStateIdAndHealthBlockCodeAndHealthFacilityTypeId(Integer stateCode,Integer blockCode,Integer facilityTypeId);
    HealthFacility findAllByStateIdAndHealthBlockCodeAndHealthFacilityTypeIdAndHealthFacilityCode(Integer stateCode,Integer blockCode,Integer facilityTypeId,Integer facilityCode);
    HealthFacility findAllByStateIdAndHealthBlockCodeAndDistrictCodeAndHealthFacilityCode(Integer stateCode,Integer blockCode,Integer districtCode,Integer facilityCode);


}
