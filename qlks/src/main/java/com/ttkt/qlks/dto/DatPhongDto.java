package com.ttkt.qlks.dto;

import com.ttkt.qlks.entity.Phong;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class DatPhongDto {
    private String tenKH;
    private int soPhong;
    private Phong.LoaiPhong loaiPhong;
    private Phong.TrangThaiPhong trangThaiPhong;
}
