package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.Phong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongRepository extends JpaRepository<Phong,Integer> {
    Page<Phong> findByLoaiPhongAndTrangThaiPhong(Phong.LoaiPhong loaiPhong, Phong.TrangThaiPhong trangThaiPhong, Pageable pageable);
    Phong findByMaPhong(Integer maPhong);
    Phong findBySoPhong(Integer soPhong);
}
