package com.ttkt.qlks.form.hoadon;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class CreateHoaDonForm {
    private int maNhanVien;
    private int maDatPhong;
    private String ngayTraPhong;
    private String loaiThanhToan;
    private List<Integer> maDichVu;
}