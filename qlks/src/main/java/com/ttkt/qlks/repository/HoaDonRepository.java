package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    HoaDon findByMaHoaDon(Integer maHoaDon);
}
