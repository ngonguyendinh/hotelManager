package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter@Entity@Table(name = "dich_vu")
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dich_vu")
    private int maDichVu;
    @Column(name = "ten_dich_vu",nullable = false,length = 30)
    private String tenDichVu;
    @Column(name = "gia_dich_vu",nullable = false)
    private float giaDichVu;
    @Column(name = "loai_dich_vu")
    @Enumerated(value = EnumType.STRING)
    private LoaiDichVu loaiDichVu;

    public enum LoaiDichVu{
        DOAN,DOUONG
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = LoaiDichVu.valueOf(loaiDichVu);
    }
}
