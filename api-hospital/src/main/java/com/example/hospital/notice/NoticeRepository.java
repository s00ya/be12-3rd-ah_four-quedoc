package com.example.hospital.notice;

import com.example.hospital.notice.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByHospital_Idx(Long hospitalId);
    List<Notice> findByHospitalIdx(Long hospitalIdx);

}
