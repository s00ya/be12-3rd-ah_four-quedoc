package com.example.hospital.hospital.repository;

import com.example.hospital.hospital.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findById(Long idx);
    List<Hospital> findByNameContaining(@Param("name") String name);


    List<Hospital> findAllByName(String name);

    List<Hospital> findByTypeContaining(String type,PageRequest of);

    List<Hospital> findByDepartmentContaining(String type,PageRequest of);

    List<Hospital> findByAddressContaining(String type,PageRequest of);
}
