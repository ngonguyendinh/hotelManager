package com.ttkt.qlks.dto;

import com.ttkt.qlks.entity.NhanVien;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class NhanVienDto {

    private String tenNhanVien;

    private String diaChi;

    private String sdt;

    private String email;
    private String username;
    private NhanVien.ChucVu chucVu;
}
