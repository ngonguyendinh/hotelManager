package com.ttkt.qlks.dto;

import com.ttkt.qlks.entity.Phong;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PhongDto {
    private int soPhong;
    private float giaPhong;
    private Phong.LoaiPhong loaiPhong;
    private Phong.TrangThaiPhong trangThaiPhong;
}
