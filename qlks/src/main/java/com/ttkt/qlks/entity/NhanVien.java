package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhan_vien")
    private int maNhanVien;
    @Column(name = "ten_nhan_vien",nullable = false,length = 30)
    private String tenNhanVien;
    @Column(name = "dia_chi",nullable = false,length = 255)
    private String diaChi;
    @Column(name = "sdt",nullable = false,length = 10,unique = true)
    private String sdt;
    @Column(name = "email",nullable = false,length = 50,unique = true)
    private String email;
    @Column(name = "password",nullable = false,length = 255)
    private String password;
    @Column(name = "chuc_vu",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ChucVu chucVu;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons;

    public void setChucVu(String chucVu) {
        this.chucVu = ChucVu.valueOf(chucVu);
    }

    public enum ChucVu{
        QUANLY,NHANVIEN
    }
}
