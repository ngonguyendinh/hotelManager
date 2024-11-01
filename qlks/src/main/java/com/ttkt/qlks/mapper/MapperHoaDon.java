package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.HoaDonDto;
import com.ttkt.qlks.entity.HoaDon;
import com.ttkt.qlks.form.hoadon.CreateHoaDonForm;

public class MapperHoaDon {
    public static HoaDon map(CreateHoaDonForm form){
        var hoaDon = new HoaDon();
        return hoaDon;
    }
    public static HoaDonDto map(HoaDon hoaDon){
        var dto = new HoaDonDto();
        dto.setTenKhachHang(hoaDon.getDatPhong().getKhachHang().getTenKhachHang());
        dto.setMaDatPhong(hoaDon.getDatPhong().getMaDatPhong());
        dto.setThoiGianNhan(hoaDon.getDatPhong().getThoiGianDat());
        dto.setThoiGianTra(hoaDon.getThoiGianTraPhong());
        dto.setLoaiPhong(hoaDon.getDatPhong().getPhong().getLoaiPhong());
        dto.setTongTien(hoaDon.getTongTien());
        return dto;
    }
}
