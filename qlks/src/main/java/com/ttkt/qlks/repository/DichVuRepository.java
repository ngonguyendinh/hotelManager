package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.DichVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DichVuRepository extends JpaRepository<DichVu,Integer> {

    Page<DichVu> findByLoaiDichVu(DichVu.LoaiDichVu loaiDichVu, Pageable pageable);
    DichVu findByMaDichVu(Integer maDichVU);

}
