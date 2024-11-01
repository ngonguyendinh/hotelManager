package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma_khach_hang")
    private Long maKhachHang;

    @Column(name="ten_khach_hang",nullable = false,length = 50)
    private String tenKhachHang;

    @Column(name="dia_chi",nullable = false,length = 150)
    private String diaChi;

    @Column(name = "email",nullable = false,length = 50)
    private String email;

    @Column(name = "sdt",nullable = false,length = 10)
    private String sdt;

    @Column(name = "cccd",nullable = false,unique = true,length = 12)
    private String cccd;

    @Column(name = "lan_den_cuoi_cung", updatable = false,nullable = false)
    @UpdateTimestamp
    private LocalDateTime lanDenCuoiCung;

    @OneToMany(mappedBy = "khachHang")
    private List<DatPhong> datPhongs;
}
