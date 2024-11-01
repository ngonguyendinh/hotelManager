package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.DatPhong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatPhongRepository extends JpaRepository<DatPhong,Integer> {
    DatPhong findByMaDatPhong(Integer maDatPhong);
}
