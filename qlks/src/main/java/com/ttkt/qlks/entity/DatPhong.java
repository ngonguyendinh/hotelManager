package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter@Getter
@Table(name = "dat_phong")
public class DatPhong {
    @Id@Column(name = "ma_dat_phong")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDatPhong;

    @Column(name = "thoi_gian_dat",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime thoiGianDat;


    @ManyToOne
    @JoinColumn(name="ma_khach_hang",referencedColumnName = "ma_khach_hang")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "ma_phong",referencedColumnName = "ma_phong")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Phong phong;

    @OneToOne(mappedBy = "datPhong")
    private HoaDon hoaDon;

}
