package com.ttkt.qlks.dto;

import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.entity.ThanhToan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter@Getter
public class HoaDonDto {
    private int maNhanVien;
    private List<String> tenDichVu;
    private String tenKhachHang;
    private Phong.LoaiPhong loaiPhong;
    private String thoiGianSuDung;
    private int maDatPhong;
    private LocalDateTime thoiGianNhan;
    private LocalDateTime thoiGianTra;
    private ThanhToan.LoaiThanhToan loaiThanhToan;
    private float tongTien;
}
