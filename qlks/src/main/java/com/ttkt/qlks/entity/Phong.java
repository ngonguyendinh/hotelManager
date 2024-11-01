package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter@Getter
@Table(name="phong")
public class Phong {
    @Id@Column(name = "ma_phong")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maPhong;

    @Column(name = "so_phong",nullable = false)
    private int soPhong;

    @Column(name = "loai_phong", length = 10,nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoaiPhong loaiPhong;

    @Column(name = "trang_thai_phong", length = 10,nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TrangThaiPhong trangThaiPhong;

    @Column(name = "gia_phong",nullable = false,length = 25)
    private float giaPhong;
    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = LoaiPhong.valueOf(loaiPhong);
    }
    public void setTrangThaiPhong(String trangThaiPhong){
        this.trangThaiPhong = TrangThaiPhong.valueOf(trangThaiPhong);
    }
    @OneToMany(mappedBy = "phong")
    private List<DatPhong> datPhongs;

    public enum LoaiPhong{
        VIP,THUONG
    }
    public enum TrangThaiPhong{
        CON,HET
    }
}
