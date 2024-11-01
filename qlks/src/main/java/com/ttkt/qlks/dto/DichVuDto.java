package com.ttkt.qlks.dto;

import com.ttkt.qlks.entity.DichVu;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class DichVuDto {
    private String tenDichVu;
    private float giaDichVu;
    private DichVu.LoaiDichVu loaiDichVu;
}
