package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.KhachHang;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    Page<KhachHang> findByTenKhachHangLike(String tenKhachHang, Pageable pageable);
    KhachHang findByMaKhachHang(Integer maKH);
    KhachHang findByCccd(String cccd);

}
