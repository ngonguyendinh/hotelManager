package com.ttkt.qlks.repository;

import com.ttkt.qlks.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
    Page<NhanVien> findByTenNhanVienLike(String tenNhanVien,Pageable pageable);
    NhanVien findByEmail(String email);

}
