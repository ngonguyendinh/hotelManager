package com.ttkt.qlks.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class KhachHangDto {
    private String tenKhachHang;
    private String sdt;
    private String email;
    private String diaChi;
    private LocalDateTime lanDenCuoiCung;
}
