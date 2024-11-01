package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.DatPhongDto;
import com.ttkt.qlks.entity.DatPhong;
import com.ttkt.qlks.form.datphong.CreateDatPhongForm;

public class MapperDatPhong {
    public static DatPhong map(CreateDatPhongForm form){
        var datPhong = new DatPhong();
        return datPhong;
    }
    public static DatPhongDto map(DatPhong datPhong){
        var dto = new DatPhongDto();
        dto.setTenKH(datPhong.getKhachHang().getTenKhachHang());
        dto.setSoPhong(datPhong.getPhong().getSoPhong());
        dto.setLoaiPhong(datPhong.getPhong().getLoaiPhong());
        dto.setTrangThaiPhong(datPhong.getPhong().getTrangThaiPhong());
        return dto;
    }
}
